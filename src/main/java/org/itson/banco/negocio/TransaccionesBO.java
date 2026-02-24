package org.itson.banco.negocio;

import java.math.BigDecimal;
import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;
import org.itson.banco.persistencia.PersistenciaException;
import org.itson.banco.persistencia.ITransaccionesDAO;

/**
 * Clase de la capa de negocio que implementa la lógica para la ejecución de transacciones básicas.
 * Se encarga de validar que los montos sean positivos y que la información de la cuenta
 * sea consistente antes de solicitar su registro en la base de datos.
 * @author Dario
 */
public class TransaccionesBO implements ITransaccionesBO {

    /** Acceso a la capa de datos para el registro de movimientos. */
    private final ITransaccionesDAO transaccionDAO;
    
    /**
     * Constructor que inyecta la dependencia del DAO de transacciones.
     * @param transaccionDAO Implementación de la persistencia a utilizar.
     */
    public TransaccionesBO(ITransaccionesDAO transaccionDAO){
        this.transaccionDAO = transaccionDAO;
    }
    
    /**
     * Valida y registra una transacción monetaria.
     * Reglas de negocio:
     * 1. El monto no debe ser nulo.
     * 2. El monto debe ser estrictamente mayor a cero.
     * 3. El número de cuenta debe estar presente y no ser solo espacios en blanco.
     * @param nuevaTransaccion DTO con los datos del monto y número de cuenta.
     * @return El objeto Transaccion generado tras el registro exitoso.
     * @throws NegocioException Si se violan las reglas de monto o cuenta, o si falla la persistencia.
     */
    @Override
    public Transaccion crearTransaccion(
            NuevaTransaccionDTO nuevaTransaccion
    ) throws NegocioException {
        // Validación de existencia de monto
        if (nuevaTransaccion.getMonto() == null) {
            throw new NegocioException("El monto de la transacción es obligatorio", null);
        }
        
        // Validación de monto positivo (comparación contra BigDecimal.ZERO)
        if (nuevaTransaccion.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("El monto debe ser mayor a cero", null);
        }
        
        // Validación de identificador de cuenta
        if (nuevaTransaccion.getNumeroCuenta() == null || nuevaTransaccion.getNumeroCuenta().isBlank()) {
            throw new NegocioException("El número de cuenta origen es obligatorio", null);
        }
        
        try {
            // Se delega a la persistencia tras pasar las validaciones
            return this.transaccionDAO.crearTransaccion(nuevaTransaccion);
        } catch (PersistenciaException ex) {
            // Encapsulamiento de error técnico en error de negocio
            throw new NegocioException("No se pudo registrar la transacción en el sistema", ex);
        }
    }
}
