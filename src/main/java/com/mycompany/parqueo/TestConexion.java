/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueo;

import almacenamiento.BaseDatos;
import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args) {
        Connection conn = BaseDatos.conectar();
        if (conn != null) {
            System.out.println("🔌 Conexión funcionando.");
        }
    }
}
