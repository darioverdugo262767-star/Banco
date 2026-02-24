package org.itson.banco.entidades;

/**
 * Define los estados operativos posibles de una cuenta bancaria dentro del sistema.
 * Este enumerador es utilizado para validar si una cuenta puede realizar 
 * transacciones (retiros, transferencias, depósitos) o si se encuentra restringida.
 * @author Dario
 */
public enum EstadoCuenta {
    
    /** Indica que la cuenta se encuentra operativa y puede realizar cualquier 
     * tipo de movimiento financiero. 
     */
    ACTIVA,
    
    /** Indica que la cuenta ha sido dada de baja o suspendida. 
     * Las cuentas en este estado no deben aparecer en los selectores de operación 
     * ni permitir cargos o abonos. 
     */
    INACTIVA
}
