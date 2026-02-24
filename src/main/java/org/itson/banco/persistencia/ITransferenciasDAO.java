package org.itson.banco.persistencia;

import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Transferencia;

/**
 * Interfaz que define las operaciones de persistencia para la entidad Transferencia.
 * Esta interfaz es la encargada de gestionar el registro de movimientos de fondos 
 * entre dos cuentas (origen y destino). La implementación de este método debe 
 * asegurar la atomicidad de la operación, garantizando que el retiro de la cuenta 
 * emisora y el depósito en la receptora se realicen correctamente o no se realice ninguno.
 * @author Dario
 */
public interface ITransferenciasDAO {

    /**
     * Registra una nueva transferencia en la base de datos.
     * Se recomienda que la implementación de este método invoque un Procedimiento 
     * Almacenado o utilice bloques de Transacciones SQL (commit/rollback) para 
     * actualizar los saldos de ambas cuentas y generar los registros en las tablas 
     * de historial correspondientes.
     * @param nuevaTransferencia Objeto DTO que contiene el monto, la cuenta de origen 
     * y la cuenta de destino.
     * @return El objeto Transferencia persistido con sus respectivos identificadores.
     * @throws PersistenciaException Si ocurre un error de conectividad, saldo 
     * insuficiente en la base de datos o fallo en la 
     * integridad referencial.
     */
    public abstract Transferencia crearTransferencia(
            NuevaTransferenciaDTO nuevaTransferencia
    ) throws PersistenciaException;
}
