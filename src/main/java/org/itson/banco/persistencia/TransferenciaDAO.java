/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.entidades.Transaccion;
import org.itson.banco.entidades.Transferencia;

/**
 *
 * @author Dario
 */
public class TransferenciaDAO implements ITransferenciaDAO {
    private static final Logger LOGGER = Logger.getLogger(TransferenciaDAO.class.getName());

    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaDTO nuevaTransferencia) throws PersistenciaException {
        Connection conexion = null; 

        try {
            String sqlTrans = "insert into transacciones(monto, numero_Cuenta) values (?, ?)";
            String sqlTransf = "insert into transferencias(id_transaccion, cuentaDestino) values (?, ?)";
            String sqlRestar = "UPDATE cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?";
            String sqlSumar = "UPDATE cuentas SET saldo = saldo + ? WHERE numero_cuenta = ?";

            conexion = ConexionBD.crearConexion();
            conexion.setAutoCommit(false);

            PreparedStatement comandoT = conexion.prepareStatement(sqlTrans, Statement.RETURN_GENERATED_KEYS);
            comandoT.setBigDecimal(1, nuevaTransferencia.getMonto());
            comandoT.setString(2, nuevaTransferencia.getNumeroCuenta());
            comandoT.execute();

            ResultSet rs = comandoT.getGeneratedKeys();
            int idTransaccion = 0;
            if (rs.next()) {
                idTransaccion = rs.getInt(1);
            }

            PreparedStatement comandoF = conexion.prepareStatement(sqlTransf);
            comandoF.setInt(1, idTransaccion);
            comandoF.setString(2, nuevaTransferencia.getCuentaDestino());
            comandoF.execute();

            PreparedStatement comandoRestar = conexion.prepareStatement(sqlRestar);
            comandoRestar.setBigDecimal(1, nuevaTransferencia.getMonto());
            comandoRestar.setString(2, nuevaTransferencia.getNumeroCuenta());
            comandoRestar.executeUpdate();

            PreparedStatement comandoSumar = conexion.prepareStatement(sqlSumar);
            comandoSumar.setBigDecimal(1, nuevaTransferencia.getMonto());
            comandoSumar.setString(2, nuevaTransferencia.getCuentaDestino());
            comandoSumar.executeUpdate();

            conexion.commit();
            LOGGER.fine("Se registró la transferencia y se actualizaron los saldos");

            return new Transferencia(
                    null,                
                    nuevaTransferencia.getCuentaDestino(),                
                    idTransaccion,                
                    new GregorianCalendar(),                
                    nuevaTransferencia.getMonto(),                
                    nuevaTransferencia.getNumeroCuenta()                
            );

        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                    LOGGER.info("Rollback realizado");
                } catch (SQLException e) {
                    LOGGER.severe("Error en rollback: " + e.getMessage());
                }
            }
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible realizar la transferencia", ex);
        } finally {
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true); 
                    conexion.close();
                } catch (SQLException e) {
                    LOGGER.severe("Error al cerrar conexión");
                }
            }
        }
    }
    
    public List<Cuenta> consultarCuentasPorCliente(Integer idCliente) throws PersistenciaException {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT numeroCuenta, saldo, fechaApertura, id_cliente FROM cuentas WHERE id_cliente = ?";

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

                    Cuenta cuenta = new Cuenta(num, saldo, fechaApertura, idCliente);
                    cuentas.add(cuenta);
                }
            }
            return cuentas;

        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error al consultar cuentas del cliente {0}", idCliente);
            throw new PersistenciaException("No se pudieron obtener las cuentas del cliente", e);
        }
    }
    
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
}