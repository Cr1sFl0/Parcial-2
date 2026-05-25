package com.peliculas.main;

import com.peliculas.connection.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando prueba de conexión...");
        Connection conexion = DatabaseConnection.getConnection();
    }
}