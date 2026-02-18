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
public class NuevaTransaccionDTO {
    private BigDecimal monto;
    private String numeroCuenta;

    public NuevaTransaccionDTO() {
    
    }

    public NuevaTransaccionDTO(BigDecimal monto, String numeroCuenta) {
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getMonto() {
        return monto; 
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto; 
    }

    public String getNumeroCuenta() {
        return numeroCuenta; 
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta; 
    }
    
}
