package org.itson.banco.entidades;

/**
 * Clase de entidad que representa el domicilio físico de un cliente.
 * Esta clase mapea la información de ubicación geográfica vinculada a un cliente
 * específico mediante su identificador único, permitiendo el cumplimiento de
 * requisitos fiscales y de registro bancario.
 * @author Dario
 */
public class DireccionCliente {

    /** Identificador del cliente al que pertenece la dirección (Llave foránea). */
    private Integer id_cliente;
    
    /** Nombre de la vialidad del domicilio. */
    private String calle;
    
    /** Número exterior e interior de la propiedad. */
    private String numero;
    
    /** Nombre del asentamiento o colonia. */
    private String colonia;
    
    /** Nombre de la ciudad o localidad. */
    private String ciudad;
    
    /** Código postal asignado por la autoridad postal. */
    private String codigoPostal;

    /**
     * Constructor por defecto para la creación de instancias vacías.
     */
    public DireccionCliente() {
    }

    /**
     * Constructor que inicializa todos los atributos de la dirección.
     * Útil para recuperar datos completos desde la persistencia.
     * @param id_cliente Identificador único del cliente asociado.
     * @param calle Nombre de la calle.
     * @param numero Número de la vivienda.
     * @param colonia Nombre de la colonia.
     * @param ciudad Nombre de la ciudad.
     * @param codigoPostal Código postal del área.
     */
    public DireccionCliente(Integer id_cliente, String calle, String numero, String colonia, String ciudad, String codigoPostal) {
        this.id_cliente = id_cliente;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Obtiene el identificador del cliente asociado.
     * @return El ID del cliente.
     */
    public Integer getId_cliente() {
        return id_cliente;
    }

    /**
     * Establece el identificador del cliente asociado.
     * @param id_cliente El ID del cliente a asignar.
     */
    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Obtiene el nombre de la calle.
     * @return Cadena con el nombre de la calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece el nombre de la calle.
     * @param calle La calle a asignar.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el número de la vivienda.
     * @return Cadena con el número.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la vivienda.
     * @param numero El número a asignar.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el nombre de la colonia.
     * @return Cadena con la colonia.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece el nombre de la colonia.
     * @param colonia La colonia a asignar.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene el nombre de la ciudad.
     * @return Cadena con la ciudad.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece el nombre de la ciudad.
     * @param ciudad La ciudad a asignar.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el código postal.
     * @return Cadena con el código postal.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el código postal.
     * @param codigoPostal El código postal a asignar.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
