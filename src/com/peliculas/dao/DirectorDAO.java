package com.peliculas.dao;

import com.peliculas.connection.DatabaseConnection;
import com.peliculas.model.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DirectorDAO {

    // Método para adicionar un registro en la BD
    public void agregarDirector(Director director) {
        String sql = "INSERT INTO directores (nombre, pais) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Aquí pasamos los datos del objeto a la consulta SQL de forma segura
            pstmt.setString(1, director.getNombre());
            pstmt.setString(2, director.getPais());

            pstmt.executeUpdate();
            System.out.println("¡Director agregado con éxito a la base de datos!");

        } catch (SQLException e) {
            System.out.println("Error al agregar director: " + e.getMessage());
        }
    }
}