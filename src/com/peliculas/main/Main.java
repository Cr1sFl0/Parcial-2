package com.peliculas.main;

import com.peliculas.dao.DirectorDAO;
import com.peliculas.dao.PeliculaDAO;
import com.peliculas.model.Director;
import com.peliculas.model.Pelicula;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirectorDAO directorDAO = new DirectorDAO();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        int opcion = -1;

        do {
            System.out.println("\n=== GESTOR DE PELÍCULAS Y DIRECTORES ===");
            System.out.println("1. Adicionar un Director");
            System.out.println("2. Consultar TODOS los directores");
            System.out.println("3. Consultar UN director por ID");
            System.out.println("4. Filtrar directores por PAÍS");
            System.out.println("5. Actualizar un director");
            System.out.println("6. Eliminar un director");
            System.out.println("7. Agregar Película a un Director");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del director: "); String nom = scanner.nextLine();
                        System.out.print("País: "); String pais = scanner.nextLine();
                        directorDAO.agregarDirector(nom, pais); // Asegúrate de tener este método en DirectorDAO
                        break;
                    case 2:
                        List<Director> lista = directorDAO.obtenerTodos();
                        for (Director d : lista) {
                            System.out.println(d.getId() + ". " + d.getNombre() + " (" + d.getPais() + ")");
                            for (Pelicula p : d.getPeliculas()) {
                                System.out.println("   -> " + p.getTitulo() + " | " + p.getGenero() + " (" + p.getAnio() + ")");
                            }
                        }
                        break;
                    case 3:
                        System.out.print("ID del director: "); int idConsultar = scanner.nextInt();
                        Director d = directorDAO.buscarPorId(idConsultar); // Asegúrate de tener este método en DirectorDAO
                        if (d != null) System.out.println(d.getNombre() + " - " + d.getPais());
                        else System.out.println("No encontrado.");
                        break;
                    case 4:
                        System.out.print("País a filtrar: "); String paisFiltro = scanner.nextLine();
                        List<Director> filtrados = directorDAO.filtrarPorPais(paisFiltro);
                        filtrados.forEach(dir -> System.out.println(dir.getNombre()));
                        break;
                    case 5:
                        System.out.print("ID a actualizar: "); int idUpd = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nuevo nombre: "); String nomUpd = scanner.nextLine();
                        System.out.print("Nuevo país: "); String paiUpd = scanner.nextLine();
                        directorDAO.actualizarDirector(idUpd, nomUpd, paiUpd);
                        break;
                    case 6:
                        System.out.print("ID a eliminar: "); int idDel = scanner.nextInt();
                        directorDAO.eliminarDirector(idDel);
                        break;
                    case 7:
                        System.out.print("Título: "); String tit = scanner.nextLine();
                        System.out.print("Género: "); String gen = scanner.nextLine();
                        System.out.print("Año: "); int anio = scanner.nextInt();
                        System.out.print("ID Director: "); int idDir = scanner.nextInt();
                        peliculaDAO.agregarPelicula(tit, gen, anio, idDir);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.next();
            }
        } while (opcion != 0);
        scanner.close();
    }
}