package org.itson.banco.negocio;

import java.math.BigDecimal;
import java.util.List;
import org.itson.banco.dtos.NuevaCuentaDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.persistencia.ICuentasDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 * Clase de la capa de negocio encargada de gestionar las reglas relacionadas
 * con las cuentas bancarias.
 * Actúa como intermediario entre la vista (presentación) y la persistencia (DAO), 
 * validando que los datos de las cuentas cumplan con los requisitos mínimos de 
 * integridad y lógica bancaria antes de ser almacenados o procesados.
 * @author Dario
 */
public class CuentasBO implements ICuentasBO {
    
    /** Acceso a la capa de datos para la gestión de cuentas. */
    private final ICuentasDAO cuentasDAO;
    
    /**
     * Constructor que inyecta la dependencia del DAO de cuentas.
     * @param cuentasDAO Implementación de la persistencia de cuentas.
     */
    public CuentasBO(ICuentasDAO cuentasDAO){
        this.cuentasDAO = cuentasDAO;
    }

    /**
     * Procesa la creación de una nueva cuenta bancaria validando las reglas de negocio.
     * Reglas aplicadas:
     * 1. El número de cuenta no debe ser nulo ni estar vacío.
     * 2. El saldo inicial no puede ser un valor negativo.
     * @param nuevaCuenta DTO con la información de la cuenta a crear.
     * @param clienteSesion El cliente que actualmente tiene la sesión iniciada.
     * @return El objeto Cuenta una vez persistido en la base de datos.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en la persistencia.
     */
    @Override
    public Cuenta crearCuenta(
            NuevaCuentaDTO nuevaCuenta, Cliente clienteSesion
    ) throws NegocioException {   
        
        // Validación de nulidad y contenido del número de cuenta
        if (nuevaCuenta.getNumeroCuenta() == null ||
            nuevaCuenta.getNumeroCuenta().isBlank()) {
            throw new NegocioException("El número de cuenta es obligatorio", null);
        }
        
        // Validación de saldo positivo
        if (nuevaCuenta.getSaldo().doubleValue() < 0) {
            throw new NegocioException("El saldo inicial no puede ser negativo", null);
        }
        
        try {
            // Llamada a la persistencia pasando el ID del cliente logueado
            return cuentasDAO.crearCuenta(nuevaCuenta, clienteSesion.getId());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear la cuenta", ex);
        }
    }
    
    @Override
    public List<Cuenta> consultarCuentasPorCliente(Integer idCliente) throws NegocioException {
        try {
            return this.cuentasDAO.consultarCuentasPorCliente(idCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las cuentas del cliente", ex);
        }
    }

    @Override
    public void desactivarCuenta(String numeroCuenta) throws NegocioException {
        try {
            this.cuentasDAO.desactivarCuenta(numeroCuenta);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al intentar dar de baja la cuenta", ex);
        }
    }
    
    @Override
    public BigDecimal consultarSaldoCuenta(String numeroCuenta) throws NegocioException {
        try {
            return this.cuentasDAO.consultarSaldoCuenta(numeroCuenta);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el saldo de la cuenta", ex);
        }
    }
    
    @Override
    public boolean existeCuenta(String numeroCuenta) throws NegocioException {
        try {
            return cuentasDAO.existeCuenta(numeroCuenta);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al verificar la existencia de la cuenta", ex);
        }
    }
}