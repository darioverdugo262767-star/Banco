/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.pruebaconexionjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.itson.banco.persistencia.ConexionBD;

/**
 *
 * @author Dario
 */
public class PruebaConexionJDBC {

    private static final System.Logger LOGGER = System.getLogger(PruebaConexionJDBC.class.getName());
    
    public static void main(String[] args) {
        try {
            Connection conexion = ConexionBD.crearConexion();
            
            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery("select id, nombres, apellidoPaterno, apellidoMaterno from clientes;");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombres"));
                System.out.println("Apellido Paterno: " + rs.getString("apellidoPaterno"));
                System.out.println("Apellido Materno: " + rs.getString("apellidoMaterno"));
                System.out.println("------------------------");
            }

            conexion.close();

        } catch (Exception e) {
            System.out.println("Fallo");
            e.printStackTrace();
        }
    }
}
