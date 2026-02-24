package org.itson.banco.persistencia;

/**
 * Excepción personalizada para la capa de persistencia del sistema bancario.
 * Se utiliza para capturar y relanzar errores específicos de la base de datos 
 * como java.sql.SQLException de una manera controlada. Esto permite 
 * desacoplar la lógica de acceso a datos de las capas superiores, facilitando 
 * el mantenimiento y la lectura de logs de error.
 * @author Dario
 */
public class PersistenciaException extends Exception {

    /**
     * Construye una nueva excepción de persistencia con un mensaje detallado 
     * y la causa original del fallo.
     * @param message Mensaje que describe el error ocurrido en la persistencia.
     * @param cause El error original (generalmente una excepción de SQL) que 
     * provocó este fallo.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
