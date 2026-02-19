/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import java.math.BigDecimal;
import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;
import org.itson.banco.persistencia.ITransaccionDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 *
 * @author Dario
 */
public class TransaccionBO implements ITransaccionBO{

    private final ITransaccionDAO transaccionDAO;
    
    public TransaccionBO(ITransaccionDAO automovilesDAO){
        this.transaccionDAO = automovilesDAO;
    }
    
    @Override
    public Transaccion crearTransaccion(
            NuevaTransaccionDTO nuevaTransaccion
    ) throws NegocioException {
        if (nuevaTransaccion.getMonto() == null) {
            throw new NegocioException("El monto de la transacción es obligatorio", null);
        }
        if (nuevaTransaccion.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("El monto debe ser mayor a cero", null);
        }
        if (nuevaTransaccion.getNumeroCuenta() == null || nuevaTransaccion.getNumeroCuenta().isBlank()) {
            throw new NegocioException("El número de cuenta origen es obligatorio", null);
        }
        try {
            Transaccion transaccion = this.transaccionDAO.crearTransaccion(nuevaTransaccion);
            return transaccion;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo registrar la transacción en el sistema", ex);
        }
    }
    
}
