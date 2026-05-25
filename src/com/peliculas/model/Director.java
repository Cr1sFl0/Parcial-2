package com.peliculas.model;

import java.util.ArrayList;
import java.util.List;

public class Director {
    private int id;
    private String nombre;
    private String pais;
    private List<Pelicula> peliculas = new ArrayList<>();

    // Constructor completo
    public Director(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public List<Pelicula> getPeliculas() { return peliculas; }

    // Setters
    public void setPeliculas(List<Pelicula> peliculas) { this.peliculas = peliculas; }

    @Override
    public String toString() {
        return "Director [ID=" + id + ", Nombre=" + nombre + ", País=" + pais + "]";
    }
}