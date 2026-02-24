package org.itson.banco.dtos;

import java.math.BigDecimal;

/**
 * Objeto de Transferencia de Datos (DTO) especializado para el registro de 
 * transferencias entre cuentas bancarias.
 * Hereda de NuevaTransaccionDTO para reutilizar los atributos de monto 
 * y cuenta de origen, añadiendo la información necesaria de la cuenta receptora.
 * @author Dario
 */
public class NuevaTransferenciaDTO extends NuevaTransaccionDTO{
    /** El número de cuenta que recibirá los fondos de la transferencia. */
    private String cuentaDestino;

    /**
     * Constructor por defecto. Invoca al constructor de la clase base.
     */
    public NuevaTransferenciaDTO() {
        super();
    }

    /**
     * Constructor que inicializa los datos de la transferencia.
     * @param numeroCuenta El número de cuenta de origen (emisor).
     * @param cuentaDestino El número de cuenta de destino (receptor).
     * @param monto La cantidad de dinero a transferir.
     */
    public NuevaTransferenciaDTO(String numeroCuenta, String cuentaDestino, BigDecimal monto) {
        super(monto, numeroCuenta);
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * Obtiene el número de cuenta de destino.
     * @return Una cadena con el identificador de la cuenta receptora.
     */
    public String getCuentaDestino() {
        return cuentaDestino; 
    }

    /**
     * Establece el número de cuenta de destino.
     * @param cuentaDestino El número de cuenta que recibirá el monto.
     */
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
      
}
