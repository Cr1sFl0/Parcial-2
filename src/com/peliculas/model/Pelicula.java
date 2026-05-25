package com.peliculas.model;

public class Pelicula {
    private int id;
    private String titulo;
    private String genero;
    private int anio;
    private int directorId;

    // Constructor vacío
    public Pelicula() {
    }

    // Constructor con parámetros
    public Pelicula(int id, String titulo, String genero, int anio, int directorId) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.directorId = directorId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getDirectorId() { return directorId; }
    public void setDirectorId(int directorId) { this.directorId = directorId; }

    @Override
    public String toString() {
        return "Película [ID=" + id + ", Título=" + titulo + ", Género=" + genero +
                ", Año=" + anio + ", ID Director=" + directorId + "]";
    }
}