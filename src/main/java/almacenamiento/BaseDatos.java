/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package almacenamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/parqueo2025";
    private static final String USER = "root";
    private static final String PASSWORD = "Parqueo2025";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✔ Conectado a MySQL correctamente");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a MySQL: " + e.getMessage());
            return null;
        }
    }
}