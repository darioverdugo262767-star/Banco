/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
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
        try {
            String sqlTrans = "insert into transacciones(monto, numero_Cuenta) values (?, ?)";
            String sqlTransf = "insert into transferencias(id_transaccion, cuentaDestino) values (?, ?)";

            Connection conexion = ConexionBD.crearConexion();
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

            LOGGER.fine("Se registro la transferencia");

            return new Transferencia(
                    null,
                    nuevaTransferencia.getCuentaDestino(),
                    idTransaccion,
                    new GregorianCalendar(),
                    nuevaTransferencia.getMonto(),
                    nuevaTransferencia.getNumeroCuenta()
            );

        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible agregar la transferencia", ex);
        }
    }
}