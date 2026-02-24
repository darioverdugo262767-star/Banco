package org.itson.banco.negocio;

import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;

/**
 * Interfaz que define los servicios de la capa de negocio para la gestión de transacciones.
 * Establece el contrato para el registro de movimientos financieros básicos, 
 * asegurando que se validen las reglas de disponibilidad y existencia de cuentas 
 * antes de afectar los saldos en la base de datos.
 * @author Dario
 */
public interface ITransaccionesBO {

    /**
     * Procesa y registra una nueva transacción monetaria en el sistema.
     * El método debe verificar que el monto sea válido y que la cuenta asociada 
     * tenga los permisos necesarios para realizar el movimiento.
     * @param nuevaTransaccion Objeto DTO con el monto y número de cuenta de la operación.
     * @return El objeto Transaccion con los datos registrados y su identificador único.
     * @throws NegocioException Si el monto es inválido, la cuenta no existe, 
     * tiene fondos insuficientes o si ocurre un error en la capa de persistencia.
     */
    public abstract Transaccion crearTransaccion(
        NuevaTransaccionDTO nuevaTransaccion
    ) throws NegocioException;
    
}
