package org.itson.banco.negocio;

import java.math.BigDecimal;
import java.util.List;
import org.itson.banco.dtos.NuevaCuentaDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Cuenta;

/**
 * Interfaz que define las operaciones de negocio para la gestión de cuentas bancarias.
 * Proporciona los métodos necesarios para la apertura de nuevas cuentas,
 * asegurando que se apliquen las validaciones de saldo y propiedad del cliente 
 * antes de proceder a la persistencia de los datos.
 * @author Dario
 */
public interface ICuentasBO {

    /**
     * Registra una nueva cuenta bancaria vinculada a un cliente en sesión.
     * El método debe validar que los datos de la cuenta sean correctos y que 
     * el cliente proporcionado tenga los permisos necesarios para realizar la apertura.
     * @param nuevaCuenta Objeto DTO que contiene el número de cuenta y el saldo inicial.
     * @param clienteSesion El objeto Cliente que será el titular de la cuenta.
     * @return El objeto Cuenta con los datos registrados y el estado inicial ACTIVA.
     * @throws NegocioException Si el número de cuenta es inválido, el saldo es negativo,
     * o si ocurre un error en la comunicación con la capa de persistencia.
     */
    public abstract Cuenta crearCuenta(
        NuevaCuentaDTO nuevaCuenta, Cliente clienteSesion
    ) throws NegocioException;
    
    /**
     * Obtiene la lista de cuentas activas de un cliente.
     * @param idCliente Identificador único del cliente.
     * @return Lista de cuentas.
     * @throws NegocioException Si hay error en la consulta.
     */
    List<Cuenta> consultarCuentasPorCliente(Integer idCliente) throws NegocioException;

    /**
     * Desactiva una cuenta (baja lógica).
     * @param numeroCuenta Número de la cuenta a dar de baja.
     * @throws NegocioException Si la cuenta no se puede desactivar.
     */
    void desactivarCuenta(String numeroCuenta) throws NegocioException;
    
    /**
    * Obtiene el saldo disponible de una cuenta específica.
    * @param numeroCuenta El número de la cuenta a consultar.
    * @return El saldo en formato BigDecimal.
    * @throws NegocioException Si la cuenta no existe o hay error de conexión.
    */
    BigDecimal consultarSaldoCuenta(String numeroCuenta) throws NegocioException;
    
    /**
     * Verifica si una cuenta existe en el sistema.
     * Utilizado principalmente para validar destinatarios de transferencias.
     * @param numeroCuenta El número de cuenta a buscar.
     * @return true si la cuenta existe y está activa, false en caso contrario.
     * @throws NegocioException Si hay un error técnico.
     */
    boolean existeCuenta(String numeroCuenta) throws NegocioException;
}
