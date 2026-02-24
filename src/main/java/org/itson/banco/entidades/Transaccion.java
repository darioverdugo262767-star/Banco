package org.itson.banco.entidades;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Clase de entidad que representa una transacción monetaria general en el sistema.
 * Mapea los datos históricos de cualquier movimiento financiero, incluyendo 
 * depósitos y retiros, vinculándolos a una cuenta específica y registrando 
 * el momento exacto de la operación.
 * @author Dario
 */
public class Transaccion {
    /** Identificador único de la transacción (Autoincrementable en BD). */
    private Integer id;
    
    /** Fecha y hora exacta en la que se procesó el movimiento. */
    private GregorianCalendar fechaHora;
    
    /** Cantidad de dinero involucrada en la operación. */
    private BigDecimal monto;
    
    /** Número de la cuenta bancaria que originó o recibió el movimiento. */
    private String numero_Cuenta;

    /**
     * Constructor por defecto para la creación de instancias vacías.
     */
    public Transaccion() {
    }

    /**
     * Constructor que inicializa todos los atributos de la transacción.
     * Útil para reconstruir el historial de movimientos desde la base de datos.
     * @param id Identificador único de la transacción.
     * @param fechaHora Momento cronológico de la operación.
     * @param monto Valor monetario del movimiento.
     * @param numero_Cuenta Número de cuenta asociado.
     */
    public Transaccion(Integer id, GregorianCalendar fechaHora, BigDecimal monto, String numero_Cuenta) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.numero_Cuenta = numero_Cuenta;
    }

    /**
     * Obtiene el identificador único de la transacción.
     * @return El ID de la transacción.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la transacción.
     * @param id El nuevo ID a asignar.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora de la operación.
     * @return Un objeto GregorianCalendar con el registro temporal.
     */
    public GregorianCalendar getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la operación.
     * @param fechaHora El registro temporal a asignar.
     */
    public void setFechaHora(GregorianCalendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el monto de la transacción.
     * @return Un BigDecimal con la cantidad operada.
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción.
     * @param monto El valor monetario a asignar.
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el número de cuenta relacionado con la transacción.
     * @return Cadena con el número de cuenta.
     */
    public String getNumero_Cuenta() {
        return numero_Cuenta;
    }

    /**
     * Establece el número de cuenta relacionado con la transacción.
     * @param numero_Cuenta El número de cuenta a asignar.
     */
    public void setNumero_Cuenta(String numero_Cuenta) {
        this.numero_Cuenta = numero_Cuenta;
    } 
}
