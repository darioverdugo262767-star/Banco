/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.dtos;

import java.util.GregorianCalendar;

/**
 *
 * @author Dario
 */
public class NuevoClienteDTO {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private GregorianCalendar fechaNacimiento;

    public NuevoClienteDTO() {
    
    }

    public NuevoClienteDTO(String nombres, String apellidoPaterno, String apellidoMaterno, GregorianCalendar fechaNacimiento, Integer edad) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return nombres; 
    }
    
    public String getApellidoPaterno() {
        return apellidoPaterno; 
    }
    
    public String getApellidoMaterno() {
        return apellidoMaterno; 
    }
    
    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento; 
    }
    
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento; 
    }
    
    public String getNombreCompleto() {
        return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
    }
}
