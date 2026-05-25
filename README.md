# Gestor de Películas y Directores - Parcial 2 🎬

Este es un proyecto en Java desarrollado para el segundo parcial de Programación Orientada a Objetos. Permite gestionar una base de datos de Directores y Películas utilizando el patrón DAO y conexión a una base de datos remota en Neon (PostgreSQL).

## 🚀 Funcionalidades (Menú Infinito)
- Adicionar un registro (Director)
- Consultar TODOS los registros
- Consultar UN registro por ID
- Filtrar directores por PAÍS (Criterio elaborado)
- Actualizar un registro
- Eliminar un registro

## 🛠️ Tecnologías Usadas
- **Java 17+**
- **PostgreSQL (Neon Tech)**
- **Git y GitHub**

## 🔒 Configuración de Credenciales (Importante para el evaluador)
Por motivos de seguridad, la contraseña de la base de datos no está expuesta en este repositorio. 
Para ejecutar el proyecto, debe dirigirse a la clase `DatabaseConnection.java` ubicada en `src/com/peliculas/connection/` y reemplazar el valor de la variable `password` (que actualmente dice `"PONER_AQUI_LA_CONTRASEÑA"`) por la contraseña real suministrada por el estudiante.

## 📊 Diagrama Entidad-Relación (Modelo)
A continuación, se presenta la estructura de la base de datos implementada:

```mermaid
erDiagram
    DIRECTOR ||--o{ PELICULA : "dirige"
    DIRECTOR {
        int id PK
        string nombre
        string pais
    }
    PELICULA {
        int id PK
        string titulo
        string genero
        int anio
        int director_id FK
    }
