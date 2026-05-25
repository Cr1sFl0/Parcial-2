package com.peliculas.main;

import com.peliculas.dao.DirectorDAO;
import com.peliculas.model.Director;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando el programa...");

        // 1. Creamos un objeto de tipo Director con datos reales
        Director nuevoDirector = new Director();
        nuevoDirector.setNombre("Christopher Nolan"); // Puedes poner el director que quieras
        nuevoDirector.setPais("Reino Unido");

        // 2. Usamos nuestro DAO para enviar ese director a la base de datos Neon
        DirectorDAO directorDAO = new DirectorDAO();
        directorDAO.agregarDirector(nuevoDirector);

        System.out.println("Proceso finalizado.");
    }
}