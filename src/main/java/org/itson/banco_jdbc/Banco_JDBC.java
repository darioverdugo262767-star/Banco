package org.itson.banco_jdbc;

import org.itson.banco.negocio.ClientesBO;
import org.itson.banco.negocio.IClientesBO;
import org.itson.banco.persistencia.ClientesDAO;
import org.itson.banco.persistencia.IClientesDAO;
import org.itson.banco.presentacion.LoginFrame;

/**
 * Clase principal (Entry Point) del sistema bancario.
 * Se encarga de orquestar el inicio de la aplicación mediante la configuración 
 * de las dependencias necesarias entre las capas de persistencia, negocio 
 * y presentación antes de lanzar la interfaz gráfica de usuario.
 * @author Dario
 */
public class Banco_JDBC {

    /**
     * Punto de entrada principal del programa.
     * Realiza la instanciación de los componentes siguiendo un flujo de 
     * inyección de dependencias de abajo hacia arriba:
     * 1. Crea el DAO (Persistencia).
     * 2. Inyecta el DAO en el BO (Negocio).
     * 3. Inyecta el BO en el Frame principal (Presentación).
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Inicialización de la capa de datos
        IClientesDAO clientesDAO = new ClientesDAO();  
        
        // Inicialización de la capa de negocio con su dependencia
        IClientesBO clientesBO = new ClientesBO(clientesDAO);
        
        // Inicialización de la interfaz gráfica e inicio de la aplicación
        LoginFrame login = new LoginFrame(clientesBO);
        login.setVisible(true);
    }
    
}