package com.peliculas.dao;

import com.peliculas.connection.DatabaseConnection;
import java.sql.*;

public class PeliculaDAO {
    public void agregarPelicula(String titulo, String genero, int anio, int directorId) {
        String sql = "INSERT INTO peliculas (titulo, genero, anio, director_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, genero);
            pstmt.setInt(3, anio);
            pstmt.setInt(4, directorId);
            pstmt.executeUpdate();
            System.out.println("¡Película agregada con éxito!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}