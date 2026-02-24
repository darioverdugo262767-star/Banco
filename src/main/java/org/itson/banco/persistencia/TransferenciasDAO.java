package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Transferencia;

/**
 * Implementación de la persistencia para transferencias bancarias.
 * Maneja la lógica de base de datos para mover fondos entre cuentas, asegurando 
 * la integridad de los datos mediante el uso de transacciones SQL (Commit/Rollback).
 * @author Dario
 */
public class TransferenciasDAO implements ITransferenciasDAO {
    
    private static final Logger LOGGER = Logger.getLogger(TransferenciasDAO.class.getName());

    /**
     * Ejecuta una transferencia bancaria completa.
     * El proceso incluye:
     * 1. Insertar el registro base en la tabla 'transacciones'.
     * 2. Recuperar el ID generado.
     * 3. Insertar el detalle en la tabla 'transferencias'.
     * 4. Actualizar el saldo de la cuenta origen (restar).
     * 5. Actualizar el saldo de la cuenta destino (sumar).
     * @param nuevaTransferencia DTO con los datos de las cuentas y el monto.
     * @return Objeto Transferencia con la información registrada.
     * @throws PersistenciaException Si ocurre algún error en SQL; se ejecuta rollback automáticamente.
     */
    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaDTO nuevaTransferencia) throws PersistenciaException {
        Connection conexion = null; 

        try {
            conexion = ConexionBD.crearConexion();
            // Desactivamos el auto-commit para manejar la transacción manualmente
            conexion.setAutoCommit(false);
            
            String sqlTrans = "insert into transacciones(monto, numero_Cuenta, fechaHora) values (?, ?, ?)";
            String sqlTransf = "insert into transferencias(id_transaccion, cuentaDestino) values (?, ?)";
            String sqlRestar = "update cuentas set saldo = saldo - ? where numeroCuenta = ?";
            String sqlSumar = "update cuentas set saldo = saldo + ? where numeroCuenta = ?";

            // 1. Registro en Transacciones
            PreparedStatement comandoT = conexion.prepareStatement(sqlTrans, Statement.RETURN_GENERATED_KEYS);
            comandoT.setBigDecimal(1, nuevaTransferencia.getMonto());
            comandoT.setString(2, nuevaTransferencia.getNumeroCuenta());
            comandoT.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            comandoT.execute();

            // 2. Obtener ID generado para la relación
            ResultSet rs = comandoT.getGeneratedKeys();
            int idTransaccion = 0;
            if (rs.next()) {
                idTransaccion = rs.getInt(1);
            }

            // 3. Registro en Transferencias (Detalle)
            PreparedStatement comandoF = conexion.prepareStatement(sqlTransf);
            comandoF.setInt(1, idTransaccion);
            comandoF.setString(2, nuevaTransferencia.getCuentaDestino());
            comandoF.execute();

            // 4. Actualización Saldo Origen
            PreparedStatement comandoRestar = conexion.prepareStatement(sqlRestar);
            comandoRestar.setBigDecimal(1, nuevaTransferencia.getMonto());
            comandoRestar.setString(2, nuevaTransferencia.getNumeroCuenta());
            comandoRestar.executeUpdate();

            // 5. Actualización Saldo Destino
            PreparedStatement comandoSumar = conexion.prepareStatement(sqlSumar);
            comandoSumar.setBigDecimal(1, nuevaTransferencia.getMonto());
            comandoSumar.setString(2, nuevaTransferencia.getCuentaDestino());
            comandoSumar.executeUpdate();

            // Si todo fue exitoso, persistimos los cambios
            conexion.commit();
            LOGGER.fine("Transferencia completada con éxito. IdTransacción: " + idTransaccion);

            return new Transferencia(
                    idTransaccion,
                    nuevaTransferencia.getCuentaDestino(),
                    idTransaccion,
                    new GregorianCalendar(), 
                    nuevaTransferencia.getMonto(),                                       
                    nuevaTransferencia.getNumeroCuenta()                
            );

        } catch (SQLException ex) {
            // En caso de error, revertimos todas las operaciones para evitar estados inconsistentes
            if (conexion != null) {
                try {
                    conexion.rollback();
                    LOGGER.info("Se realizó un Rollback debido a un error en la transferencia.");
                } catch (SQLException e) {
                    LOGGER.severe("Fallo crítico al intentar rollback: " + e.getMessage());
                }
            }
            throw new PersistenciaException("No fue posible realizar la transferencia bancaria", ex);
        } finally {
            // Siempre cerramos la conexión y restauramos el auto-commit
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true); 
                    conexion.close();
                } catch (SQLException e) {
                    LOGGER.severe("Error al cerrar los recursos de conexión");
                }
            }
        }
    }
}