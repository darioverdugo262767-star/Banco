package org.itson.banco.negocio;

import java.math.BigDecimal;
import java.util.List;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.entidades.Transferencia;
import org.itson.banco.persistencia.ICuentasDAO;
import org.itson.banco.persistencia.PersistenciaException;
import org.itson.banco.persistencia.ITransferenciasDAO;

/**
 * Clase de la capa de negocio que implementa la lógica para transferencias bancarias.
 * Coordina múltiples validaciones de seguridad y consistencia, interactuando con 
 * los DAOs de cuentas y transferencias para garantizar la integridad de la operación.
 * @author Dario
 */
public class TransferenciasBO implements ITransferenciasBO {
    
    private final ITransferenciasDAO transferenciaDAO;
    private final ICuentasDAO cuentasDAO;

    /**
     * Constructor que inicializa los DAOs necesarios para procesar transferencias.
     * @param transferenciaDAO DAO para el registro de la transferencia.
     * @param cuentasDAO DAO para validaciones de saldo y existencia de cuentas.
     */
    public TransferenciasBO(ITransferenciasDAO transferenciaDAO, ICuentasDAO cuentasDAO) {
        this.transferenciaDAO = transferenciaDAO;
        this.cuentasDAO = cuentasDAO;
    }
    
    /**
     * Ejecuta la lógica de negocio para realizar una transferencia entre cuentas.
     * Reglas de validación:
     * 1. Existencia de sesión de cliente.
     * 2. Monto positivo y válido.
     * 3. Presencia de cuentas origen y destino.
     * 4. Restricción de transferencia a la misma cuenta.
     * 5. Propiedad de la cuenta origen (debe pertenecer al cliente en sesión).
     * 6. Existencia de la cuenta destino en el sistema.
     * 7. Suficiencia de saldo en la cuenta origen.
     * @param nuevaTransferencia DTO con la información del movimiento.
     * @param clienteSesion Cliente que intenta realizar la operación.
     * @return Objeto {@link Transferencia} que confirma el registro exitoso.
     * @throws NegocioException Si falla alguna validación o hay error en persistencia.
     */
    @Override
    public Transferencia crearTransferencia(
            NuevaTransferenciaDTO nuevaTransferencia, Cliente clienteSesion
    ) throws NegocioException {
        // 1. Validaciones básicas de entrada
        if (clienteSesion == null){
            throw new NegocioException("No hay cliente en sesion", null);
        }
        if (nuevaTransferencia.getMonto() == null || nuevaTransferencia.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
                throw new NegocioException("Debe ingresar un monto válido mayor a cero", null);
        }
        if (nuevaTransferencia.getNumeroCuenta() == null || nuevaTransferencia.getNumeroCuenta().isBlank()) {
            throw new NegocioException("La cuenta de origen es obligatoria", null);
        }
        if (nuevaTransferencia.getCuentaDestino() == null || nuevaTransferencia.getCuentaDestino().isBlank()) {
            throw new NegocioException("La cuenta de destino es obligatoria", null);
        }
        
        // 2. Regla: No transferir a sí mismo
        if (nuevaTransferencia.getNumeroCuenta().equals(nuevaTransferencia.getCuentaDestino())) {
            throw new NegocioException("No puedes realizar una transferencia a la misma cuenta de origen", null);
        }

        try {
            // 3. Validar propiedad de la cuenta (Seguridad)
            List<Cuenta> cuentasCliente = cuentasDAO.consultarCuentasPorCliente(clienteSesion.getId());
            boolean esPropia = cuentasCliente.stream()
                    .anyMatch(c -> c.getNumeroCuenta()
                    .equals(nuevaTransferencia.getNumeroCuenta()));

            if (!esPropia) {
                throw new NegocioException("La cuenta origen no pertenece al cliente", null);
            }

            // 4. Validar existencia del destino
            if (!cuentasDAO.existeCuenta(nuevaTransferencia.getCuentaDestino())) {
                throw new NegocioException("La cuenta destino no existe", null);
            }

            // 5. Validar saldo suficiente
            BigDecimal saldoActual = cuentasDAO.consultarSaldoCuenta(nuevaTransferencia.getNumeroCuenta());
            if (saldoActual.compareTo(nuevaTransferencia.getMonto()) < 0) {
                throw new NegocioException("Saldo insuficiente", null);
            }

            // 6. Ejecución de la transferencia
            return this.transferenciaDAO.crearTransferencia(nuevaTransferencia);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error interno al procesar la transferencia", ex);
        }
    }
}
        