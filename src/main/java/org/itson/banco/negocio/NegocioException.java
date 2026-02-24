package org.itson.banco.negocio;

/**
 * Excepción personalizada para la capa de negocio del sistema bancario.
 * Esta clase se utiliza para encapsular errores relacionados con las reglas 
 * de negocio (como saldo insuficiente, cliente menor de edad, etc.) y para 
 * envolver excepciones de niveles inferiores (como SQLException) de manera 
 * que la interfaz de usuario reciba mensajes claros y controlados.
 * @author Dario
 */
public class NegocioException extends Exception {

    /**
     * Construye una nueva excepción de negocio con un mensaje descriptivo
     * y la causa original del error.
     * @param message Mensaje amigable que describe el error.
     * @param cause El objeto Throwable que originó el error.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
