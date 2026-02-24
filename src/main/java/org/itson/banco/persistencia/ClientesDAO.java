package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaDireccionClienteDTO;
import org.itson.banco.dtos.NuevoClienteDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.utileria.Metodos;

/**
 * Implementación de la persistencia para la entidad Cliente.
 * Maneja las operaciones CRUD y validaciones directamente con la base de datos MySQL,
 * asegurando la atomicidad en el registro de clientes y sus domicilios.
 * @author Dario
 */
public class ClientesDAO implements IClientesDAO {

    private static final Logger LOGGER = Logger.getLogger(ClientesDAO.class.getName());
    
    /**
     * Registra un nuevo cliente y su dirección en una sola transacción atómica.
     * Si el registro del cliente o de la dirección falla, se realiza un rollback
     * para mantener la integridad de la base de datos.
     * @param nuevoCliente DTO con los datos del cliente.
     * @param nuevaDireccion DTO con los datos del domicilio.
     * @return El objeto Cliente persistido con su ID generado.
     * @throws PersistenciaException Si ocurre un error de SQL durante la ejecución.
     */
    @Override
    public Cliente crearCliente(
            NuevoClienteDTO nuevoCliente,
            NuevaDireccionClienteDTO nuevaDireccion
    ) throws PersistenciaException {
        Connection conexion = null;

        try {
            conexion = ConexionBD.crearConexion();
            // Iniciamos transacción manual
            conexion.setAutoCommit(false);

            String sqlCliente = """
                insert into Clientes
                (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, edad, contrasenia)
                values (?, ?, ?, ?, ?, ?);
            """;

            PreparedStatement comandoCliente = conexion.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);

            comandoCliente.setString(1, nuevoCliente.getNombres());
            comandoCliente.setString(2, nuevoCliente.getApellidoPaterno());
            comandoCliente.setString(3, nuevoCliente.getApellidoMaterno());
            comandoCliente.setString(4, Metodos.formatearSQL(nuevoCliente.getFechaNacimiento()));
            comandoCliente.setInt(5, Metodos.calcularEdad(nuevoCliente.getFechaNacimiento()));
            comandoCliente.setString(6, nuevoCliente.getContraseña());

            comandoCliente.executeUpdate();

            // Recuperamos el ID autoincrementable generado por la BD
            ResultSet rs = comandoCliente.getGeneratedKeys();
            Integer idGenerado = null;

            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            
            String sqlDireccion = """
                insert into DireccionesCliente
                (id_cliente, calle, numero, colonia, ciudad, codigoPostal)
                values (?, ?, ?, ?, ?, ?);
            """;

            PreparedStatement comandoDireccion = conexion.prepareStatement(sqlDireccion);

            comandoDireccion.setInt(1, idGenerado);
            comandoDireccion.setString(2, nuevaDireccion.getCalle());
            comandoDireccion.setString(3, nuevaDireccion.getNumero());
            comandoDireccion.setString(4, nuevaDireccion.getColonia());
            comandoDireccion.setString(5, nuevaDireccion.getCiudad());
            comandoDireccion.setString(6, nuevaDireccion.getCodigoPostal());

            comandoDireccion.executeUpdate();

            // Si llegamos aquí sin errores, guardamos los cambios definitivamente
            conexion.commit();

            return new Cliente(
                    idGenerado,
                    nuevoCliente.getNombres(),
                    nuevoCliente.getApellidoPaterno(),
                    nuevoCliente.getApellidoMaterno(),
                    nuevoCliente.getFechaNacimiento(),
                    Metodos.calcularEdad(nuevoCliente.getFechaNacimiento()),
                    nuevoCliente.getContraseña()
            );

        } catch (SQLException ex) {
            // Si algo falla, revertimos cualquier cambio hecho en esta transacción
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new PersistenciaException("No fue posible registrar el cliente", ex);

        } finally {
            try {
                if (conexion != null) {
                    conexion.setAutoCommit(true);
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Busca un cliente en la base de datos basándose en su nombre de pila.
     * @param nombre Nombre a buscar.
     * @return Cliente encontrado o null si no existe.
     * @throws PersistenciaException Error al consultar la base de datos.
     */
    public Cliente buscarPorNombre(String nombre) throws PersistenciaException {
        String sql = "select * from clientes where nombres = ?";

        try (Connection conexion = ConexionBD.crearConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GregorianCalendar fechaNac = Metodos.convertirSqlADate(rs.getDate("fechaNacimiento"));
                    return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        fechaNac,
                        rs.getInt("edad"),
                        rs.getString("contrasenia")
                    );
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar cliente", e);
        }
        return null;
    }
    
    /**
     * Valida las credenciales de acceso comparando el nombre completo concatenado
     * y la contraseña contra los registros de la base de datos.
     * @param nombreCompleto Nombres y apellidos unidos por espacios.
     * @param contraseña Clave de acceso.
     * @return El Cliente autenticado o null si los datos son incorrectos.
     * @throws PersistenciaException Error de conexión o consulta.
     */
    @Override
    public Cliente validarLogin(String nombreCompleto, String contraseña) throws PersistenciaException {
        String sql = "SELECT id, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, edad, contrasenia " +
                     "FROM Clientes WHERE " +
                     "CONCAT(nombres, ' ', apellidoPaterno, ' ', apellidoMaterno) = ?" +
                     "AND contrasenia = ?";

        try (Connection conexion = ConexionBD.crearConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, nombreCompleto.trim());
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GregorianCalendar fechaNac = Metodos.convertirSqlADate(rs.getDate("fechaNacimiento"));
                    
                    return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        fechaNac,
                        rs.getInt("edad"),
                        rs.getString("contrasenia")
                    );
                }
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al validar credenciales", ex);
        }
        return null;
    }
}
