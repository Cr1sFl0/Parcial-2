package com.peliculas.model;

public class Director {
    private int id;
    private String nombre;
    private String pais;

    // Constructor vacío
    public Director() {
    }

    // Constructor con parámetros
    public Director(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    @Override
    public String toString() {
        return "Director [ID=" + id + ", Nombre=" + nombre + ", País=" + pais + "]";
    }
}