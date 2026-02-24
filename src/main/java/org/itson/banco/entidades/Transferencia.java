package org.itson.banco.entidades;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Clase de entidad que representa una transferencia bancaria entre dos cuentas.
 * Esta clase extiende de Transaccion, especializándola al añadir una 
 * cuenta de destino y un identificador específico para la relación de transferencia.
 * Representa el registro histórico de un movimiento de fondos de una cuenta 
 * emisora a una receptora.
 * @author Dario
 */
public class Transferencia extends Transaccion {

    /** Identificador único de la transferencia en la tabla correspondiente. */
    private Integer id_transferencia;
    
    /** Número de la cuenta que recibe los fondos. */
    private String cuentaDestino;

    /**
     * Constructor por defecto.
     */
    public Transferencia() {
        super();
    }

    /**
     * Constructor que inicializa los atributos de la transferencia y de su clase padre.
     * @param id_transferencia Identificador específico de la transferencia.
     * @param cuentaDestino Número de cuenta del beneficiario.
     * @param id Identificador general de la transacción.
     * @param fechaHora Fecha y hora en que se realizó el movimiento.
     * @param monto Cantidad de dinero transferida.
     * @param numero_Cuenta Número de la cuenta de origen (emisor).
     */
    public Transferencia(Integer id_transferencia, String cuentaDestino, Integer id, GregorianCalendar fechaHora, BigDecimal monto, String numero_Cuenta) {
        super(id, fechaHora, monto, numero_Cuenta);
        this.id_transferencia = id_transferencia; // Corregido para usar el parámetro id_transferencia
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * Obtiene el identificador específico de la transferencia.
     * @return El ID de la transferencia.
     */
    public Integer getId_transferencia() {
        return id_transferencia;
    }

    /**
     * Establece el identificador específico de la transferencia.
     * @param id_transferencia El nuevo ID a asignar.
     */
    public void setId_transferencia(Integer id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    /**
     * Obtiene el número de cuenta que recibió los fondos.
     * @return Cadena con el número de cuenta destino.
     */
    public String getCuentaDestino() {
        return cuentaDestino;
    }

    /**
     * Establece el número de cuenta que recibirá los fondos.
     * @param cuentaDestino El número de cuenta destino a asignar.
     */
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}