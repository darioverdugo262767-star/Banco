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
public class Transaccion {
    private Integer id;
    private GregorianCalendar fechaHora;
    private BigDecimal monto;
    private String numero_Cuenta;

    public Transaccion() {
        
    }

    public Transaccion(Integer id, GregorianCalendar fechaHora, BigDecimal monto, String numero_Cuenta) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.numero_Cuenta = numero_Cuenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GregorianCalendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(GregorianCalendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getNumero_Cuenta() {
        return numero_Cuenta;
    }

    public void setNumero_Cuenta(String numero_Cuenta) {
        this.numero_Cuenta = numero_Cuenta;
    } 
    
}
