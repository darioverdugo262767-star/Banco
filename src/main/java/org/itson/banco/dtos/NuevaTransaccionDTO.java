package org.itson.banco.dtos;

import java.math.BigDecimal;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información mínima 
 * necesaria para registrar una transacción bancaria.
 * Se utiliza principalmente para capturar el monto y la cuenta involucrada 
 * en operaciones de retiro o depósito antes de ser procesadas por el sistema.
 * @author Dario
 */
public class NuevaTransaccionDTO {
    /** El monto económico de la transacción. */
    private BigDecimal monto;
    /** El número de cuenta asociado a la transacción. */
    private String numeroCuenta;

    /**
     * Constructor por defecto.
     */
    public NuevaTransaccionDTO() {
    
    }

    /**
     * Constructor que inicializa los atributos de la transacción.
     * @param monto El valor monetario de la operación.
     * @param numeroCuenta El identificador de la cuenta destino u origen.
     */
    public NuevaTransaccionDTO(BigDecimal monto, String numeroCuenta) {
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene el monto de la transacción.
     * @return Un BigDecimal que representa la cantidad de dinero.
     */
    public BigDecimal getMonto() {
        return monto; 
    }

    /**
     * Obtiene el número de cuenta relacionado.
     * @return Una cadena de texto con el número de cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta; 
    } 

    /**
     * Establece el monto de la transacción.
     * @param monto El monto a asignar.
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Establece el número de cuenta relacionado.
     * @param numeroCuenta El número de cuenta a asignar.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
 
}
