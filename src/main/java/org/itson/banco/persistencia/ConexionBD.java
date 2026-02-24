package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad encargada de gestionar la conexión con el servidor 
 * de base de datos MySQL.
 * Centraliza los parámetros de acceso como la URL del servidor, el usuario 
 * y la contraseña, facilitando la creación de objetos {@link Connection} 
 * para las clases de la capa de persistencia.
 * @author Dario
 */
public class ConexionBD {
    
    /**
    * Constructor por defecto.
    */
    public ConexionBD() {
        // Constructor vacío para cumplir con el estándar de documentación
    }
    
    /** 
     * URL de conexión JDBC que incluye el host, puerto, nombre de la BD 
     * y parámetros de codificación de caracteres. 
     */
    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/banco_py1?useUnicode=true&characterEncoding=UTF8";
    
    /** Usuario con privilegios de acceso a la base de datos. */
    private static final String USUARIO = "root";
    
    /** Contraseña del usuario de la base de datos. */
    private static final String CONTRASEÑA = "357642";
    
    /**
     * Establece y retorna una conexión activa con la base de datos MySQL 
     * utilizando el DriverManager.
     * @return Un objeto Connection para interactuar con la base de datos.
     * @throws SQLException Si ocurre un error al intentar establecer el enlace, 
     * como credenciales incorrectas o servidor apagado.
     */
    public static Connection crearConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(
                ConexionBD.CADENA_CONEXION,
                ConexionBD.USUARIO,
                ConexionBD.CONTRASEÑA
        );
        return conexion;
    }
    
}
