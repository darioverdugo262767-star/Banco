/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.banco.persistencia;

import java.math.BigDecimal;
import java.util.List;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.entidades.Transferencia;

/**
 *
 * @author Dario
 */
public interface ITransferenciaDAO {
    public abstract Transferencia crearTransferencia(
            NuevaTransferenciaDTO nuevaTranferencia
    ) throws PersistenciaException;
    
    List<Cuenta> consultarCuentasPorCliente(Integer idCliente) throws PersistenciaException;
    
    BigDecimal consultarSaldoCuenta(String numeroCuenta) throws PersistenciaException;
    
    boolean existeCuenta(String numeroCuenta) throws PersistenciaException;
}
