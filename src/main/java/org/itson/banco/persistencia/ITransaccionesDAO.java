package org.itson.banco.persistencia;

import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;

/**
 * Interfaz que define las operaciones de persistencia para la entidad Transaccion.
 * Establece el contrato para registrar cualquier movimiento monetario en la 
 * base de datos, garantizando que cada operación quede debidamente auditada 
 * con su respectiva fecha, monto y cuenta asociada.
 * @author Dario
 */
public interface ITransaccionesDAO {

    /**
     * Inserta un nuevo registro de transacción en la base de datos.
     * Este método es responsable de persistir los detalles básicos de un 
     * movimiento financiero y devolver el objeto con su identificador generado.
     * @param nuevaTransaccion Objeto DTO que contiene el monto y el número de cuenta.
     * @return El objeto Transaccion persistido con su ID y marca de tiempo.
     * @throws PersistenciaException Si ocurre un error de SQL o fallos en la 
     * conexión con el servidor de base de datos.
     */
    public abstract Transaccion crearTransaccion(
            NuevaTransaccionDTO nuevaTransaccion
    ) throws PersistenciaException;
}