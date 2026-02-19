/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;

/**
 *
 * @author Dario
 */
public class TransaccionDAO implements ITransaccionDAO{
    private static final Logger LOGGER = Logger.getLogger(TransaccionDAO.class.getName());

    @Override
    public Transaccion crearTransaccion(
            NuevaTransaccionDTO nuevaTransaccion
    ) throws PersistenciaException {
        try{
            String codigoSQL = """
                insert into transacciones(monto, numero_Cuenta)
                values (?, ?);
            """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            
            comando.setBigDecimal(1, nuevaTransaccion.getMonto());
            comando.setString(2, nuevaTransaccion.getNumeroCuenta());

            comando.execute();
            
            LOGGER.fine("Se registro la transaccion");
            
            return new Transaccion(
                    null,
                    new GregorianCalendar(),
                    nuevaTransaccion.getMonto(),
                    nuevaTransaccion.getNumeroCuenta()
            );
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible registrar la transaccion", ex);
        }
    }
}

