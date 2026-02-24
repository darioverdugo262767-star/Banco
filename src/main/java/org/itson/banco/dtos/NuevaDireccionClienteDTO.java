package org.itson.banco.dtos;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información de la 
 * dirección de un cliente necesaria para su registro en el sistema.
 * Se utiliza para transportar los datos del domicilio desde la interfaz de 
 * usuario hacia las capas de lógica y persistencia.
 * @author Dario
 */
public class NuevaDireccionClienteDTO {
    /** Nombre de la calle del domicilio. */
    private String calle;
    /** Número exterior e interior (opcional) de la vivienda. */
    private String numero;
    /** Nombre de la colonia o fraccionamiento. */
    private String colonia;
    /** Nombre de la ciudad de residencia. */
    private String ciudad;
    /** Código postal de la zona geográfica. */
    private String codigoPostal;

    /**
     * Constructor por defecto.
     */
    public NuevaDireccionClienteDTO() {
        
    }
    
    /**
     * Constructor que inicializa todos los atributos de la dirección del cliente.
     * @param calle Nombre de la calle.
     * @param numero Número de la vivienda.
     * @param colonia Nombre de la colonia.
     * @param ciudad Nombre de la ciudad.
     * @param codigoPostal Código postal.
     */
    public NuevaDireccionClienteDTO(String calle, String numero, String colonia, String ciudad, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }
    
    /**
     * Obtiene el nombre de la calle.
     * @return Una cadena con el nombre de la calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Obtiene el número de la vivienda.
     * @return Una cadena con el número.
     */
    public String getNumero() {
        return numero;
    }
    
    /**
     * Obtiene el nombre de la colonia.
     * @return Una cadena con la colonia.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Obtiene el nombre de la ciudad.
     * @return Una cadena con la ciudad.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Obtiene el código postal.
     * @return Una cadena con el código postal.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el nombre de la calle.
     * @param calle El nombre de la calle a asignar.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Establece el número de la vivienda.
     * @param numero El número a asignar.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Establece el nombre de la colonia.
     * @param colonia El nombre de la colonia a asignar.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Establece el nombre de la ciudad.
     * @param ciudad El nombre de la ciudad a asignar.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Establece el código postal.
     * @param codigoPostal El código postal a asignar.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
}
