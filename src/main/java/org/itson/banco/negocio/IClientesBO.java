package org.itson.banco.negocio;

import org.itson.banco.dtos.NuevaDireccionClienteDTO;
import org.itson.banco.dtos.NuevoClienteDTO;
import org.itson.banco.entidades.Cliente;

/**
 * Interfaz que define los servicios de la capa de negocio para la gestión de clientes.
 * Establece los métodos necesarios para el registro de nuevos usuarios y la 
 * validación de credenciales de acceso al sistema bancario.
 * @author Dario
 */
public interface IClientesBO {

    /**
     * Registra un nuevo cliente en el sistema junto con su dirección.
     * Este método debe validar que el cliente cumpla con las reglas de negocio
     * (como la mayoría de edad) antes de enviarlo a la capa de persistencia.
     * @param nuevoCliente Objeto DTO con los datos personales del cliente.
     * @param nuevaDireccion Objeto DTO con los datos del domicilio del cliente.
     * @return El Cliente registrado con sus datos completos e identificador único.
     * @throws NegocioException Si ocurre un error en las validaciones de negocio 
     * o en la comunicación con la base de datos.
     */
    public abstract Cliente crearCliente(
        NuevoClienteDTO nuevoCliente, NuevaDireccionClienteDTO nuevaDireccion
    ) throws NegocioException;
    
    /**
     * Realiza el proceso de autenticación de un cliente en el sistema.
     * Valida que el nombre completo y la contraseña coincidan con los registros
     * existentes en la base de datos.
     * @param nombreCompleto El nombre completo del cliente (Nombres + Apellidos).
     * @param password La clave de acceso del cliente.
     * @return El objeto Cliente que representa al usuario autenticado.
     * @throws NegocioException Si las credenciales son inválidas o existe un 
     * problema técnico en el proceso de login.
     */
    public abstract Cliente iniciarSesion(
            String nombreCompleto, String password
    ) throws NegocioException;
    
}