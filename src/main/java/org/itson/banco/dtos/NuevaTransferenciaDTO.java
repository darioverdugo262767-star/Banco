/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

import java.math.BigDecimal;

/**
 *
 * @author Dario
 */
public class NuevaTransferenciaDTO extends NuevaTransaccionDTO{
    private String cuentaDestino;

    public NuevaTransferenciaDTO() {
        super();
    }

    public NuevaTransferenciaDTO(String numeroCuenta, String cuentaDestino, BigDecimal monto) {
        super(monto, numeroCuenta);
        this.cuentaDestino = cuentaDestino;
    }

    public String getCuentaDestino() {
        return cuentaDestino; 
    }
}
