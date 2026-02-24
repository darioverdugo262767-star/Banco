package org.itson.banco.persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaCuentaDTO;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.entidades.EstadoCuenta;

/**
 * Clase que implementa la persistencia de las cuentas bancarias.
 * Proporciona métodos para el registro, consulta de saldos y gestión del estado 
 * operativo de las cuentas en la base de datos MySQL.
 * @author Dario
 */
public class CuentasDAO implements ICuentasDAO {

    private static final Logger LOGGER = Logger.getLogger(CuentasDAO.class.getName());
    
    /**
     * Registra una nueva cuenta en la base de datos vinculada a un cliente específico.
     * Por defecto, toda cuenta nueva se crea con el estado ACTIVA.
     * @param nuevaCuenta DTO con el número de cuenta y saldo inicial.
     * @param idCliente Identificador único del cliente titular.
     * @return El objeto Cuenta creado.
     * @throws PersistenciaException Si ocurre un error durante la inserción en SQL.
     */
    @Override
    public Cuenta crearCuenta(NuevaCuentaDTO nuevaCuenta, Integer idCliente) throws PersistenciaException {
        try (Connection conexion = ConexionBD.crearConexion()) {
            String sql = """
                insert into Cuentas
                (numeroCuenta, saldo, fechaApertura, id_cliente, estado)
                values (?, ?, ?, ?, ?);
            """;

            PreparedStatement comando = conexion.prepareStatement(sql);
            comando.setString(1, nuevaCuenta.getNumeroCuenta());
            comando.setBigDecimal(2, nuevaCuenta.getSaldo());
            
            java.sql.Timestamp fechaActual = new java.sql.Timestamp(System.currentTimeMillis());
            comando.setTimestamp(3, fechaActual);
            comando.setInt(4, idCliente);
            comando.setString(5, EstadoCuenta.ACTIVA.name());
            
            comando.executeUpdate();
            LOGGER.fine("Se registró la cuenta con estado ACTIVA");

            return new Cuenta(
                    nuevaCuenta.getNumeroCuenta(),
                    nuevaCuenta.getSaldo(),
                    new GregorianCalendar(),
                    idCliente,
                    EstadoCuenta.ACTIVA
            );

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible crear la cuenta", ex);
        }
    }
    
    /**
     * Consulta todas las cuentas activas pertenecientes a un cliente.
     * @param idCliente ID del cliente a consultar.
     * @return Lista de objetos Cuenta asociados al cliente.
     * @throws PersistenciaException Si hay un error en la consulta SQL.
     */
    @Override
    public List<Cuenta> consultarCuentasPorCliente(Integer idCliente) throws PersistenciaException {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT numeroCuenta, saldo, fechaApertura, id_cliente FROM cuentas WHERE id_cliente = ? AND estado = 'ACTIVA'";

        try (Connection conexion = ConexionBD.crearConexion(); 
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String num = rs.getString("numeroCuenta");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    java.sql.Date fechaSql = rs.getDate("fechaApertura");

                    GregorianCalendar fechaApertura = new GregorianCalendar();
                    if (fechaSql != null) {
                        fechaApertura.setTime(fechaSql);
                    }

                    Cuenta cuenta = new Cuenta(num, saldo, fechaApertura, idCliente, EstadoCuenta.ACTIVA);
                    cuentas.add(cuenta);
                }
            }
            return cuentas;

        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error al consultar cuentas del cliente {0}", idCliente);
            throw new PersistenciaException("No se pudieron obtener las cuentas del cliente", e);
        }
    }
    
    /**
     * Obtiene el saldo actual de una cuenta específica.
     * @param numeroCuenta El número identificador de la cuenta.
     * @return El saldo como BigDecimal.
     * @throws PersistenciaException Si la cuenta no existe o hay un error de conexión.
     */
    @Override
    public BigDecimal consultarSaldoCuenta(String numeroCuenta) throws PersistenciaException {
        String sql = "SELECT saldo FROM cuentas WHERE numeroCuenta = ?";

        try (Connection conexion = ConexionBD.crearConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, numeroCuenta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("saldo");
                } else {
                    throw new PersistenciaException("Cuenta no encontrada.", null);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar saldo.", e);
        }
    }
    
    /**
     * Verifica si una cuenta existe en el sistema mediante su número de cuenta.
     * @param numeroCuenta El número a validar.
     * @return true si existe, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    @Override
    public boolean existeCuenta(String numeroCuenta) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM cuentas WHERE numeroCuenta = ?";
        try (Connection conexion = ConexionBD.crearConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, numeroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al validar la cuenta.", e);
        }
        return false;
    }
    
    /**
     * Realiza una baja lógica de la cuenta, cambiando su estado a 'INACTIVA'.
     * Una cuenta inactiva no aparecerá en las consultas normales de operación.
     * @param numCuenta El número de cuenta a desactivar.
     * @throws PersistenciaException Si ocurre un error en la actualización.
     */
    @Override
    public void desactivarCuenta(String numCuenta) throws PersistenciaException {
        String sql = "UPDATE Cuentas SET estado = 'INACTIVA' WHERE numeroCuenta = ?";
        try (Connection con = ConexionBD.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numCuenta);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error SQL al desactivar cuenta", ex);
        }
    }
}