package org.itson.banco.persistencia;

import java.math.BigDecimal;
import java.util.List;
import org.itson.banco.dtos.NuevaCuentaDTO;
import org.itson.banco.entidades.Cuenta;

/**
 * Interfaz que define las operaciones de persistencia para la entidad Cuenta.
 * Proporciona los métodos necesarios para la administración de cuentas bancarias,
 * incluyendo la consulta de saldos, validación de existencia y gestión de estados.
 * @author Dario
 */
public interface ICuentasDAO {

    /**
     * Registra una nueva cuenta bancaria en la base de datos vinculada a un cliente.
     * @param nuevaCuenta Objeto DTO con los datos iniciales de la cuenta.
     * @param idCliente Identificador único del cliente titular de la cuenta.
     * @return El objeto Cuenta persistido.
     * @throws PersistenciaException Si ocurre un error durante la inserción en el sistema.
     */
    public abstract Cuenta crearCuenta(
        NuevaCuentaDTO nuevaCuenta, Integer idCliente
    ) throws PersistenciaException;
    
    /**
     * Recupera una lista de todas las cuentas asociadas a un cliente específico.
     * @param idCliente Identificador del cliente.
     * @return Lista de objetos Cuenta pertenecientes al cliente.
     * @throws PersistenciaException Si ocurre un error en la consulta de datos.
     */
    List<Cuenta> consultarCuentasPorCliente(
            Integer idCliente
    ) throws PersistenciaException;
    
    /**
     * Obtiene el saldo disponible de una cuenta identificada por su número único.
     * @param numeroCuenta El número de cuenta a consultar.
     * @return El saldo actual de la cuenta como BigDecimal.
     * @throws PersistenciaException Si la cuenta no existe o hay errores de conexión.
     */
    BigDecimal consultarSaldoCuenta(
            String numeroCuenta
    ) throws PersistenciaException;
    
    /**
     * Verifica la existencia de una cuenta en la base de datos.
     * @param numeroCuenta El número de cuenta a validar.
     * @return {@code true} si la cuenta existe, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error en la validación.
     */
    boolean existeCuenta(
            String numeroCuenta
    ) throws PersistenciaException;
    
    /**
     * Realiza una baja lógica de la cuenta, cambiando su estado operativo.
     * @param numCuenta El número de cuenta que se desea desactivar.
     * @throws PersistenciaException Si ocurre un error al actualizar el estado en la base de datos.
     */
    void desactivarCuenta(String numCuenta) throws PersistenciaException;
    
}
