package org.itson.banco.negocio;

import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Transferencia;

/**
 * Interfaz que define los servicios de la capa de negocio para la ejecución 
 * de transferencias entre cuentas.
 * Gestiona la lógica crítica de mover fondos entre una cuenta de origen y una 
 * de destino, asegurando que se cumplan todas las reglas de seguridad, 
 * disponibilidad de saldo y pertenencia de la cuenta emisora.
 * @author Dario
 */
public interface ITransferenciasBO {

    /**
     * Procesa y ejecuta una transferencia electrónica de fondos.
     * El método debe implementar las siguientes reglas de negocio:
     * 1. Validar que la cuenta de origen pertenezca al cliente en sesión.
     * 2. Verificar que la cuenta de origen tenga saldo suficiente.
     * 3. Validar que ambas cuentas (origen y destino) estén en estado ACTIVA.
     * 4. Asegurar que la cuenta de origen y destino no sean la misma.
     * @param nuevaTransferencia DTO con los datos del emisor, receptor y monto.
     * @param clienteSesion El cliente que solicita la operación (dueño de la cuenta origen).
     * @return El objeto Transferencia con el registro de la operación realizada.
     * @throws NegocioException Si hay saldo insuficiente, cuentas inactivas, 
     * datos inválidos o fallos en la transacción de base de datos.
     */
    public abstract Transferencia crearTransferencia(
            NuevaTransferenciaDTO nuevaTransferencia, Cliente clienteSesion
    ) throws NegocioException;
}
