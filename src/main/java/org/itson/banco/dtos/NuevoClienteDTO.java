package org.itson.banco.dtos;

import java.util.GregorianCalendar;

/**
 * Objeto de Transferencia de Datos (DTO) que transporta la información personal 
 * y de seguridad necesaria para registrar un nuevo cliente en el sistema.
 * Se utiliza para comunicar la capa de presentación con la capa de persistencia
 * durante el proceso de alta de usuarios.
 * @author Dario
 */
public class NuevoClienteDTO {
    /** Nombres del cliente. */
    private String nombres;
    /** Primer apellido del cliente. */
    private String apellidoPaterno;
    /** Segundo apellido del cliente (opcional en algunos sistemas). */
    private String apellidoMaterno;
    /** Fecha de nacimiento utilizada para validaciones de mayoría de edad. */
    private GregorianCalendar fechaNacimiento;
    /** Contraseña de acceso al sistema (debe ser tratada con seguridad). */
    private String contraseña;

    
    /**
     * Constructor por defecto.
     */
    public NuevoClienteDTO() {
    
    }

    /**
     * Constructor que inicializa todos los campos del nuevo cliente.
     * @param nombres Los nombres del cliente.
     * @param apellidoPaterno El primer apellido.
     * @param apellidoMaterno El segundo apellido.
     * @param fechaNacimiento La fecha de nacimiento.
     * @param contraseña La clave de seguridad para el acceso.
     */
    public NuevoClienteDTO(String nombres, String apellidoPaterno, String apellidoMaterno, GregorianCalendar fechaNacimiento, String contraseña) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.contraseña = contraseña;
    }

    /**
     * Obtiene los nombres del cliente.
     * @return Cadena con los nombres.
     */
    public String getNombres() {
        return nombres; 
    }
    
    /**
     * Obtiene el apellido paterno.
     * @return Cadena con el apellido paterno.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno; 
    }
    
    /**
     * Obtiene el apellido materno.
     * @return Cadena con el apellido materno.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno; 
    }
    
    /**
     * Obtiene la fecha de nacimiento.
     * @return Objeto GregorianCalendar con la fecha.
     */
    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento; 
    }

    /**
     * Obtiene la contraseña del cliente.
     * @return Cadena con la contraseña.
     */
    public String getContraseña() {
        return contraseña;
    }
    
    /**
     * Genera y devuelve el nombre completo del cliente concatenando
     * nombres y apellidos.
     * @return Cadena con el nombre completo.
     */
    public String getNombreCompleto() {
        return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    /**
     * Establece los nombres del cliente.
     * @param nombres Los nombres a asignar.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Establece el apellido paterno.
     * @param apellidoPaterno El apellido paterno a asignar.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Establece el apellido materno.
     * @param apellidoMaterno El apellido materno a asignar.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Establece la fecha de nacimiento.
     * @param fechaNacimiento La fecha a asignar.
     */
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Establece la contraseña del cliente.
     * @param contraseña La clave de seguridad a asignar.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
