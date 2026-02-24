package org.itson.banco.dtos;

import java.math.BigDecimal;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información necesaria 
 * para registrar una nueva cuenta bancaria en el sistema.
 * Se utiliza para transportar datos desde la interfaz de usuario hacia la 
 * capa de persistencia sin exponer la entidad completa.
 * @author Dario
 */
public class NuevaCuentaDTO {
    /** Número identificador único de la cuenta. */
    private String numeroCuenta;
    /** Saldo inicial con el que se registrará la cuenta. */
    private BigDecimal saldo;

    /**
     * Constructor por defecto.
     */
    public NuevaCuentaDTO() {
    
    }

    /**
     * Constructor que inicializa los atributos de la cuenta.
     * @param numeroCuenta El número de cuenta único.
     * @param saldo El saldo inicial de la cuenta.
     */
    public NuevaCuentaDTO(String numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    /**
     * Obtiene el saldo de la cuenta.
     * @return Un objeto BigDecimal con el saldo.
     */
    public BigDecimal getSaldo() {
        return saldo; 
    }
    
    /**
     * Obtiene el número de cuenta.
     * @return Una cadena con el número de cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta; 
    }

    /**
     * Establece el número de cuenta.
     * @param numeroCuenta El nuevo número de cuenta.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Establece el saldo de la cuenta.
     * @param saldo El nuevo saldo inicial.
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
}
