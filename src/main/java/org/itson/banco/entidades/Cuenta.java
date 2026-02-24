package org.itson.banco.entidades;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Clase de entidad que representa una cuenta bancaria dentro del sistema.
 * Mapea la información de saldos, fechas de registro y el vínculo con el cliente,
 * además de gestionar el ciclo de vida de la cuenta mediante su estado.
 * @author Dario
 */
public class Cuenta {
    /** Identificador alfanumérico único de la cuenta. */
    private String numeroCuenta;
    
    /** Monto actual disponible en la cuenta. */
    private BigDecimal saldo;
    
    /** Fecha en la que la cuenta fue dada de alta en el sistema. */
    private GregorianCalendar fechaApertura;
    
    /** Identificador del cliente propietario de la cuenta (Llave foránea). */
    private Integer idCliente;
    
    /** Estado operativo actual de la cuenta (ACTIVA/INACTIVA). */
    private EstadoCuenta estado;

    /**
     * Constructor por defecto para la creación de instancias vacías.
     */
    public Cuenta() {
    }

    /**
     * Constructor que inicializa todos los atributos de la cuenta. 
     * Ideal para reconstruir objetos provenientes de la base de datos.
     * @param numeroCuenta El número único de cuenta.
     * @param saldo El saldo monetario disponible.
     * @param fechaApertura La fecha de registro inicial.
     * @param idCliente El identificador del cliente asociado.
     * @param estado El estado funcional de la cuenta.
     */
    public Cuenta(String numeroCuenta, BigDecimal saldo, GregorianCalendar fechaApertura, Integer idCliente, EstadoCuenta estado) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.idCliente = idCliente;
        this.estado = estado;
    }

    /**
     * Obtiene el número identificador de la cuenta.
     * @return Cadena con el número de cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta; 
    }
    
    /**
     * Establece el número identificador de la cuenta.
     * @param numeroCuenta El número de cuenta a asignar.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta; 
    }
    
    /**
     * Obtiene el saldo disponible en la cuenta.
     * @return Un BigDecimal que representa el saldo.
     */
    public BigDecimal getSaldo() {
        return saldo; 
    }
    
    /**
     * Establece el saldo de la cuenta.
     * @param saldo El nuevo saldo a asignar.
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo; 
    }
    
    /**
     * Obtiene la fecha en la que se abrió la cuenta.
     * @return Objeto GregorianCalendar con la fecha de apertura.
     */
    public GregorianCalendar getFechaApertura() {
        return fechaApertura; 
    }
    
    /**
     * Establece la fecha de apertura de la cuenta.
     * @param fechaApertura La fecha a asignar.
     */
    public void setFechaApertura(GregorianCalendar fechaApertura) {
        this.fechaApertura = fechaApertura; 
    }
    
    /**
     * Obtiene el identificador del cliente dueño de la cuenta.
     * @return El ID del cliente asociado.
     */
    public Integer getIdCliente() {
        return idCliente; 
    }
    
    /**
     * Establece el identificador del cliente dueño de la cuenta.
     * @param idCliente El ID del cliente a asignar.
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente; 
    }

    /**
     * Obtiene el estado actual de la cuenta (ACTIVA/INACTIVA).
     * @return El EstadoCuenta correspondiente.
     */
    public EstadoCuenta getEstado() {
        return estado;
    }

    /**
     * Establece el estado operativo de la cuenta.
     * @param estado El nuevo estado a asignar.
     */
    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }
}