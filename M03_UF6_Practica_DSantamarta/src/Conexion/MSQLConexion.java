/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MSQLConexion {
    
    private static Connection instance;

    public MSQLConexion() {
    }
    
    public static Connection getInstance(){
        
        if (instance == null) {
            try {
                instance = DriverManager.getConnection(DatosConexion.url,
                        DatosConexion.username, DatosConexion.password);
                System.out.println("Base de datos abierta");
            } catch (SQLException sqle) {
                System.out.println("Error al abrir la base de datos: " + sqle);
            }
        }
        return instance;
    }
    
    public static void cerrarConexion(){
        if (instance != null) {
            try {
                instance.close();
                instance = null;
                System.out.println("Base de datos cerrada");
            } catch (SQLException sqle) {
                System.out.println("Error al cerrar la base de datos");
            }
        }
    }
}
