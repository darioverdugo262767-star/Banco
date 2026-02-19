/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.negocio;

import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Transferencia;

/**
 *
 * @author Dario
 */
public interface ITransferenciaBO {
    public abstract Transferencia crearTransferencia(
            NuevaTransferenciaDTO nuevaTransferencia
    )throws NegocioException;
}
