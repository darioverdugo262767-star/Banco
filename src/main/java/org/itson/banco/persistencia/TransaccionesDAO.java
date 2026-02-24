package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;

/**
 * Clase que implementa la persistencia de transacciones básicas.
 * Se encarga de insertar los registros de movimientos monetarios en la base de datos,
 * sirviendo como base para el historial financiero de las cuentas.
 * @author Dario
 */
public class TransaccionesDAO implements ITransaccionesDAO {
    
    /** Registrador de eventos para monitorear las operaciones de la clase. */
    private static final Logger LOGGER = Logger.getLogger(TransaccionesDAO.class.getName());

    /**
     * Inserta un registro de transacción en la tabla 'transacciones'.
     * @param nuevaTransaccion Objeto DTO con el monto y número de cuenta origen.
     * @return Un objeto Transaccion que representa el movimiento registrado.
     * @throws PersistenciaException Si ocurre un error de SQL o falla la conexión.
     */
    @Override
    public Transaccion crearTransaccion(
            NuevaTransaccionDTO nuevaTransaccion
    ) throws PersistenciaException {
        // Bloque Try-with-resources recomendado para asegurar el cierre de la conexión
        try {
            String codigoSQL = """
                insert into transacciones(monto, numero_Cuenta)
                values (?, ?);
            """;
            
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            
            // Mapeo de parámetros del DTO al comando SQL
            comando.setBigDecimal(1, nuevaTransaccion.getMonto());
            comando.setString(2, nuevaTransaccion.getNumeroCuenta());

            comando.execute();
            
            LOGGER.fine("Se registró la transacción exitosamente en la base de datos.");
            
            // Retornamos el objeto entidad. 
            // Nota: El ID suele ser generado por la BD (Auto-increment).
            return new Transaccion(
                    null, 
                    new GregorianCalendar(),
                    nuevaTransaccion.getMonto(),
                    nuevaTransaccion.getNumeroCuenta()
            );
            
        } catch (SQLException ex) {
            LOGGER.severe("Error al insertar transacción: " + ex.getMessage());
            throw new PersistenciaException("No fue posible registrar la transacción", ex);
        }
    }
}

