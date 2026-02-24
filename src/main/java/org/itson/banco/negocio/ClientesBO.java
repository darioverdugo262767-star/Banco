package org.itson.banco.negocio;

import java.util.GregorianCalendar;
import org.itson.banco.dtos.NuevaDireccionClienteDTO;
import org.itson.banco.dtos.NuevoClienteDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.persistencia.IClientesDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 * Clase de la capa de negocio que implementa la lógica para la gestión de clientes.
 * Se encarga de validar las reglas de negocio, como la mayoría de edad, 
 * campos obligatorios y la correcta autenticación, sirviendo como puente entre 
 * la interfaz de usuario y la capa de persistencia.
 * @author Dario
 */
public class ClientesBO implements IClientesBO {

    /** Acceso a la capa de datos para la persistencia de clientes. */
    private final IClientesDAO clientesDAO;
    
    /**
     * Constructor que inyecta la dependencia del DAO de clientes.
     * @param clientesDAO Implementación de la persistencia a utilizar.
     */
    public ClientesBO(IClientesDAO clientesDAO){
        this.clientesDAO = clientesDAO;
    }
    
    /**
     * Procesa el registro de un nuevo cliente validando reglas de negocio.
     * Reglas aplicadas:
     * 1. Campos obligatorios: nombres, apellidos y contraseña.
     * 2. Edad: El cliente debe tener al menos 18 años cumplidos.
     * @param nuevoCliente DTO con la información personal del cliente.
     * @param nuevaDireccion DTO con la información del domicilio.
     * @return El objeto Cliente registrado con su ID generado.
     * @throws NegocioException Si no se cumplen las reglas o hay error en persistencia.
     */
    @Override
    public Cliente crearCliente(
            NuevoClienteDTO nuevoCliente,
            NuevaDireccionClienteDTO nuevaDireccion
    ) throws NegocioException {
        // Validaciones de nulidad
        if (nuevoCliente.getNombres() == null) {
            throw new NegocioException("El cliente debe de tener nombre", null);
        }
        if (nuevoCliente.getApellidoPaterno()== null) {
            throw new NegocioException("El cliente debe de tener apellido Paterno", null);
        }
        if (nuevoCliente.getApellidoMaterno()== null) {
            throw new NegocioException("El cliente debe de tener apellido Materno", null);
        }
        if (nuevoCliente.getContraseña()== null){
            throw new NegocioException("El cliente debe de crear una contraseña", null);
        }
        
        // Validación de mayoría de edad (18 años)
        long fechaHoyMillis = new GregorianCalendar().getTimeInMillis();
        long edadClienteMillis = nuevoCliente.getFechaNacimiento().getTimeInMillis();
        // 31536000000L es el aproximado de milisegundos en un año
        if((fechaHoyMillis - edadClienteMillis) / 31536000000L < 18){
            throw new NegocioException("El cliente es menor de edad", null);
        }
        
        try{
            return this.clientesDAO.crearCliente(nuevoCliente, nuevaDireccion);
        } catch(PersistenciaException ex){
            throw new NegocioException("Error al crear el cliente", ex);
        }
    } 
    
    /**
     * Gestiona el proceso de autenticación de un cliente.
     * Realiza limpieza de datos (trim) y valida las credenciales contra el DAO.
     * @param nombreCompleto Nombre completo del cliente (utilizado para login).
     * @param contraseña Clave de seguridad.
     * @return El objeto Cliente si las credenciales son válidas.
     * @throws NegocioException Si los datos son vacíos, incorrectos o falla la conexión.
     */
    @Override
    public Cliente iniciarSesion(String nombreCompleto, String contraseña) throws NegocioException {
        // 1. Limpieza de datos (Regla de negocio: no importar espacios extra)
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new NegocioException("El nombre completo no puede estar vacío.", null);
        }
        
        try {
            // 2. Consultar al DAO con el nombre limpio
            Cliente cliente = clientesDAO.validarLogin(nombreCompleto.trim(), contraseña);

            // 3. Validar si se encontró el cliente
            if (cliente == null) {
                throw new NegocioException("Nombre completo o contraseña incorrectos.", null);
            }

            return cliente;
            
        } catch (PersistenciaException ex) {
            ex.printStackTrace();
            throw new NegocioException("Error de conexión al intentar iniciar sesión.", ex);
        }
    }
}
    