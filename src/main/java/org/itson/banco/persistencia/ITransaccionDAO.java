/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.persistencia;

import org.itson.banco.dtos.NuevaTransaccionDTO;
import org.itson.banco.entidades.Transaccion;

/**
 *
 * @author Dario
 */
public interface ITransaccionDAO {
    public abstract Transaccion crearTransaccion(
            NuevaTransaccionDTO nuevaTransaccion
    ) throws PersistenciaException;
}
