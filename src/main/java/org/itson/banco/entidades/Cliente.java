/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.entidades;

import java.util.GregorianCalendar;

/**
 *
 * @author Dario
 */
public class Cliente {
    private Integer id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private GregorianCalendar fechaNacimiento;
    private Integer edad;

    public Cliente() {
        
    }

    public Cliente(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, GregorianCalendar fechaNacimiento, Integer edad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
    }

    public Integer getId() {
        return id; 
    }
    
    public void setId(Integer id) {
        this.id = id; 
    }

    public String getNombres() {
        return nombres; 
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres; 
    }

    public String getApellidoPaterno() {
        return apellidoPaterno; 
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno; 
    }

    public String getApellidoMaterno() {
        return apellidoMaterno; 
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno; 
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento; 
    }
    
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento; 
    }

    public Integer getEdad() {
        return edad; 
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad; 
    }
}