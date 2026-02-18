/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.banco.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dario
     */
public class ConexionBD {
    
    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/banco_py1";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "357642";
    
    public static Connection crearConexion() throws SQLException{
        Connection conexion = DriverManager.getConnection(
                ConexionBD.CADENA_CONEXION,
                ConexionBD.USUARIO,
                ConexionBD.CONTRASEÑA
        );
        return conexion;
    }
    
}
