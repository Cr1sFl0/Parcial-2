package com.peliculas.dao;

import com.peliculas.connection.DatabaseConnection;
import com.peliculas.model.Director;
import com.peliculas.model.Pelicula;
import java.sql.*;
import java.util.*;

public class DirectorDAO {

    // Consulta todos los directores con sus películas (JOIN), ordenado por ID
    public List<Director> obtenerTodos() {
        String sql = "SELECT d.id, d.nombre, d.pais, p.id AS p_id, p.titulo, p.genero, p.anio " +
                "FROM directores d LEFT JOIN peliculas p ON d.id = p.director_id " +
                "ORDER BY d.id ASC";

        Map<Integer, Director> mapa = new LinkedHashMap<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                mapa.putIfAbsent(id, new Director(id, rs.getString("nombre"), rs.getString("pais")));

                if (rs.getInt("p_id") != 0) {
                    Pelicula p = new Pelicula();
                    p.setId(rs.getInt("p_id"));
                    p.setTitulo(rs.getString("titulo"));
                    p.setGenero(rs.getString("genero"));
                    p.setAnio(rs.getInt("anio"));
                    mapa.get(id).getPeliculas().add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(mapa.values());
    }

    // Agregar un nuevo director
    public void agregarDirector(String nombre, String pais) {
        String sql = "INSERT INTO directores (nombre, pais) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, pais);
            ps.executeUpdate();
            System.out.println("Director agregado correctamente.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Actualizar un director existente
    public void actualizarDirector(int id, String nuevoNombre, String nuevoPais) {
        String sql = "UPDATE directores SET nombre = ?, pais = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoPais);
            pstmt.setInt(3, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) System.out.println("¡Director actualizado exitosamente!");
            else System.out.println("No se encontró el director con ID: " + id);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Eliminar un director
    public void eliminarDirector(int id) {
        String sql = "DELETE FROM directores WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if(filas > 0) System.out.println("Director eliminado correctamente.");
            else System.out.println("ID no encontrado.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Filtrar por país
    public List<Director> filtrarPorPais(String pais) {
        List<Director> lista = new ArrayList<>();
        String sql = "SELECT * FROM directores WHERE pais ILIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + pais + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                lista.add(new Director(rs.getInt("id"), rs.getString("nombre"), rs.getString("pais")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // Buscar por ID
    public Director buscarPorId(int id) {
        String sql = "SELECT * FROM directores WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new Director(rs.getInt("id"), rs.getString("nombre"), rs.getString("pais"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}