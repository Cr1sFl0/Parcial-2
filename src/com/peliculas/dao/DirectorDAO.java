package com.peliculas.dao;

import com.peliculas.connection.DatabaseConnection;
import com.peliculas.model.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO {

    public void agregarDirector(Director director) {
        String sql = "INSERT INTO directores (nombre, pais) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, director.getNombre());
            pstmt.setString(2, director.getPais());
            pstmt.executeUpdate();
            System.out.println("¡Director agregado con éxito a la base de datos!");

        } catch (SQLException e) {
            System.out.println("Error al agregar director: " + e.getMessage());
        }
    }

    public List<Director> obtenerTodos() {
        List<Director> lista = new ArrayList<>();
        String sql = "SELECT * FROM directores";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Director d = new Director();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setPais(rs.getString("pais"));
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar todos: " + e.getMessage());
        }
        return lista;
    }

    public Director obtenerPorId(int id) {
        Director d = null;
        String sql = "SELECT * FROM directores WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    d = new Director();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setPais(rs.getString("pais"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar por ID: " + e.getMessage());
        }
        return d;
    }

    // --- NUEVO MÉTODO: Filtrar por un criterio (País) ---
    public List<Director> filtrarPorPais(String pais) {
        List<Director> lista = new ArrayList<>();
        String sql = "SELECT * FROM directores WHERE pais = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pais);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Director d = new Director();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setPais(rs.getString("pais"));
                    lista.add(d);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por país: " + e.getMessage());
        }
        return lista;
    }
    // --- NUEVO MÉTODO: Actualizar un director ---
    public void actualizarDirector(int id, String nuevoNombre, String nuevoPais) {
        String sql = "UPDATE directores SET nombre = ?, pais = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoPais);
            pstmt.setInt(3, id);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Director actualizado con éxito!");
            } else {
                System.out.println("No se encontró el director con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar director: " + e.getMessage());
        }
    }

    // --- NUEVO MÉTODO: Eliminar un director ---
    public void eliminarDirector(int id) {
        String sql = "DELETE FROM directores WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Director eliminado con éxito!");
            } else {
                System.out.println("No se encontró el director con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar director: " + e.getMessage());
        }
    }
}