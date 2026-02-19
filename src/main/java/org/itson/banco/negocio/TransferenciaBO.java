/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.negocio;

import java.math.BigDecimal;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Transferencia;
import org.itson.banco.persistencia.ITransferenciaDAO;
import org.itson.banco.persistencia.PersistenciaException;

/**
 *
 * @author Dario
 */
public class TransferenciaBO implements ITransferenciaBO{
    
    private final ITransferenciaDAO transferenciaDAO;

    public TransferenciaBO(ITransferenciaDAO transferenciaDAO) {
        this.transferenciaDAO = transferenciaDAO;
    }
    
    @Override
    public Transferencia crearTransferencia(
            NuevaTransferenciaDTO nuevaTransferencia
    ) throws NegocioException {
        if (nuevaTransferencia.getMonto() == null || 
                nuevaTransferencia.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
                throw new NegocioException("Debe ingresar un monto válido mayor a cero", null);
        }
        if (nuevaTransferencia.getNumeroCuenta() == null || nuevaTransferencia.getNumeroCuenta().isBlank()) {
            throw new NegocioException("La cuenta de origen es obligatoria", null);
        }
        if (nuevaTransferencia.getCuentaDestino() == null || nuevaTransferencia.getCuentaDestino().isBlank()) {
            throw new NegocioException("La cuenta de destino es obligatoria", null);
        }
        if (nuevaTransferencia.getNumeroCuenta().equals(nuevaTransferencia.getCuentaDestino())) {
            throw new NegocioException("No puedes realizar una transferencia a la misma cuenta de origen", null);
        }
        try {
            return this.transferenciaDAO.crearTransferencia(nuevaTransferencia);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error interno al procesar la transferencia", ex);
        }
    }
}
        