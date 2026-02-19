/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.entidades;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 *
 * @author Dario
 */
public class Cuenta {
    private String numeroCuenta;
    private BigDecimal saldo;
    private GregorianCalendar fechaApertura;
    private Integer idCliente;

    public Cuenta() {
    
    }

    public Cuenta(String numeroCuenta, BigDecimal saldo, GregorianCalendar fechaApertura, Integer idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.idCliente = idCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta; 
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta; 
    }
    
    public BigDecimal getSaldo() {
        return saldo; 
    }
    
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo; 
    }
    
    public GregorianCalendar getFechaApertura() {
        return fechaApertura; 
    }
    
    public void setFechaApertura(GregorianCalendar fechaApertura) {
        this.fechaApertura = fechaApertura; 
    }
    
    public Integer getIdCliente() {
        return idCliente; 
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente; 
    }
}
