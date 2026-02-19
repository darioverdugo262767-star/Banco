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
public class NuevaCuentaDTO {
    private BigDecimal saldo;

    public NuevaCuentaDTO() {
    
    }

    public NuevaCuentaDTO(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldo() { return saldo; }
}
