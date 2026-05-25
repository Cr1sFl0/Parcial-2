package com.peliculas.main;

import com.peliculas.dao.DirectorDAO;
import com.peliculas.model.Director;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirectorDAO directorDAO = new DirectorDAO();
        int opcion = -1;

        do {
            System.out.println("\n=== GESTOR DE PELÍCULAS Y DIRECTORES ===");
            System.out.println("1. Adicionar un Director");
            System.out.println("2. Consultar TODOS los directores");
            System.out.println("3. Consultar UN director por ID");
            System.out.println("4. Filtrar directores por PAÍS");
            System.out.println("5. Actualizar un director");
            System.out.println("6. Eliminar un director");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Ingresa el nombre del director: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingresa el país del director: ");
                        String pais = scanner.nextLine();
                        Director nuevoDirector = new Director();
                        nuevoDirector.setNombre(nombre);
                        nuevoDirector.setPais(pais);
                        directorDAO.agregarDirector(nuevoDirector);
                        break;
                    case 2:
                        System.out.println("\n--- LISTA DE DIRECTORES ---");
                        for (Director d : directorDAO.obtenerTodos()) {
                            System.out.println(d.toString());
                        }
                        break;
                    case 3:
                        System.out.print("Ingresa el ID del director a buscar: ");
                        int idBuscar = scanner.nextInt();
                        Director encontrado = directorDAO.obtenerPorId(idBuscar);
                        if (encontrado != null) {
                            System.out.println("Encontrado: " + encontrado.toString());
                        } else {
                            System.out.println("No se encontró ningún director con el ID " + idBuscar);
                        }
                        break;
                    case 4:
                        System.out.print("Ingresa el país para filtrar (Ej: Reino Unido): ");
                        String paisFiltro = scanner.nextLine();
                        System.out.println("\n--- RESULTADOS DEL FILTRO ---");
                        List<Director> filtrados = directorDAO.filtrarPorPais(paisFiltro);
                        if (filtrados.isEmpty()) {
                            System.out.println("No se encontraron directores de " + paisFiltro);
                        } else {
                            for (Director d : filtrados) {
                                System.out.println(d.toString());
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Ingresa el ID del director a actualizar: ");
                        int idActualizar = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Ingresa el nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingresa el nuevo país: ");
                        String nuevoPais = scanner.nextLine();
                        directorDAO.actualizarDirector(idActualizar, nuevoNombre, nuevoPais);
                        break;
                    case 6:
                        System.out.print("Ingresa el ID del director a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        directorDAO.eliminarDirector(idEliminar);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa... ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } else {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next();
            }
        } while (opcion != 0);

        scanner.close();
    }
}