package org.itson.banco.entidades;

import java.util.GregorianCalendar;

/**
 * Clase de entidad que representa a un cliente dentro del sistema bancario.
 * Esta clase mapea directamente con la tabla 'Clientes' de la base de datos,
 * almacenando la información personal, credenciales y datos demográficos.
 * @author Dario
 */
public class Cliente {
    /** Identificador único del cliente (Llave primaria en BD). */
    private Integer id;
    /** Nombre(s) del cliente. */
    private String nombres;
    /** Primer apellido del cliente. */
    private String apellidoPaterno;
    /** Segundo apellido del cliente. */
    private String apellidoMaterno;
    /** Fecha de nacimiento almacenada en formato de calendario. */
    private GregorianCalendar fechaNacimiento;
    /** Edad calculada del cliente. */
    private Integer edad;
    /** Contraseña cifrada o en texto plano para el acceso al sistema. */
    private String contraseña;

    /**
     * Constructor por defecto para la creación de instancias vacías.
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los atributos para inicializar un cliente 
     * recuperado de la base de datos.
     * @param id Identificador único del cliente.
     * @param nombres Nombre(s) del cliente.
     * @param apellidoPaterno Primer apellido.
     * @param apellidoMaterno Segundo apellido.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param edad Edad actual.
     * @param contraseña Clave de acceso.
     */
    public Cliente(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, GregorianCalendar fechaNacimiento, Integer edad, String contraseña) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el identificador único del cliente.
     * @return El ID del cliente.
     */
    public Integer getId() {
        return id; 
    }
    
    /**
     * Establece el identificador único del cliente.
     * @param id El nuevo ID a asignar.
     */
    public void setId(Integer id) {
        this.id = id; 
    }

    /**
     * Obtiene los nombres del cliente.
     * @return Cadena con los nombres.
     */
    public String getNombres() {
        return nombres; 
    }
    
    /**
     * Establece los nombres del cliente.
     * @param nombres Los nombres a asignar.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres; 
    }

    /**
     * Obtiene el apellido paterno.
     * @return Cadena con el apellido paterno.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno; 
    }
    
    /**
     * Establece el apellido paterno.
     * @param apellidoPaterno El apellido a asignar.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno; 
    }

    /**
     * Obtiene el apellido materno.
     * @return Cadena con el apellido materno.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno; 
    }
    
    /**
     * Establece el apellido materno.
     * @param apellidoMaterno El apellido a asignar.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno; 
    }

    /**
     * Obtiene la fecha de nacimiento.
     * @return Objeto {@link GregorianCalendar} con la fecha.
     */
    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento; 
    }
    
    /**
     * Establece la fecha de nacimiento.
     * @param fechaNacimiento La fecha a asignar.
     */
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento; 
    }

    /**
     * Obtiene la edad del cliente.
     * @return Valor entero de la edad.
     */
    public Integer getEdad() {
        return edad; 
    }
    
    /**
     * Establece la edad del cliente.
     * @param edad La edad a asignar.
     */
    public void setEdad(Integer edad) {
        this.edad = edad; 
    }

    /**
     * Obtiene la contraseña del cliente.
     * @return Cadena con la clave de acceso.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del cliente.
     * @param contraseña La nueva contraseña a asignar.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}