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

    public NuevaTransferenciaDTO(BigDecimal monto, String numeroCuenta, String cuentaDestino) {
        super(monto, numeroCuenta);
        this.cuentaDestino = cuentaDestino;
    }

    public String getCuentaDestino() {
        return cuentaDestino; 
    }
    
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino; 
    }
}
