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
public class Transferencia extends Transaccion{
    private Integer id_transfencia;
    private String cuentaDestino;

    public Transferencia() {
        
    }

    public Transferencia(Integer id_transfencia, String cuentaDestino, Integer id, GregorianCalendar fechaHora, BigDecimal monto, String numero_Cuenta) {
        super(id, fechaHora, monto, numero_Cuenta);
        this.id_transfencia = id_transfencia;
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getId_transfencia() {
        return id_transfencia;
    }

    public void setId_transfencia(Integer id_transfencia) {
        this.id_transfencia = id_transfencia;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
}
