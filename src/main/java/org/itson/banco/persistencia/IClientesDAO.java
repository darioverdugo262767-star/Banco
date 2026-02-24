package org.itson.banco.persistencia;

import org.itson.banco.dtos.NuevaDireccionClienteDTO;
import org.itson.banco.dtos.NuevoClienteDTO;
import org.itson.banco.entidades.Cliente;

/**
 * Interfaz que define el contrato de persistencia para la entidad Cliente.
 * Proporciona los métodos necesarios para la manipulación y consulta de datos 
 * de los clientes en el sistema de almacenamiento.
 * @author Dario
 */
public interface IClientesDAO {

    /**
     * Persiste un nuevo cliente y su dirección en el sistema.
     * @param nuevoCliente Objeto DTO con la información personal del cliente.
     * @param nuevaDireccion Objeto DTO con los detalles del domicilio del cliente.
     * @return El objeto Cliente registrado, incluyendo su identificador generado.
     * @throws PersistenciaException Si ocurre un error técnico al interactuar con la base de datos.
     */
    public abstract Cliente crearCliente(
        NuevoClienteDTO nuevoCliente, NuevaDireccionClienteDTO nuevaDireccion
    ) throws PersistenciaException;
    
    /**
     * Realiza una búsqueda de cliente basándose en el nombre proporcionado.
     * @param nombre Nombre del cliente a buscar.
     * @return El objeto Cliente encontrado o null si no hay coincidencias.
     * @throws PersistenciaException Si ocurre un error en la consulta de datos.
     */
    public abstract Cliente buscarPorNombre(String nombre) 
        throws PersistenciaException;

    /**
     * Valida las credenciales de acceso de un cliente.
     * @param nombreCompleto Nombre completo del cliente (utilizado como identificador de acceso).
     * @param password Contraseña de seguridad del cliente.
     * @return El objeto Cliente si las credenciales son correctas, null en caso contrario.
     * @throws PersistenciaException Si ocurre un fallo en la conexión o ejecución de la consulta.
     */
    public abstract Cliente validarLogin(String nombreCompleto, String password) 
        throws PersistenciaException;
}
