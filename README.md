# Parcial 2 - Programación Orientada a Objetos

Presentado por: [Tu Nombre]
Código: [Tu Código]

## Descripción
Este proyecto corresponde a la sustentación del segundo parcial. Consiste en una aplicación Java en consola que gestiona la información de los modelos Director y Película, aplicando persistencia de datos en un servidor remoto.

## Estructura y requerimientos
- Se implementó el patrón DAO para separar la lógica de base de datos.
- Cuenta con un menú infinito iterativo.
- Permite operaciones de lectura (todos y por ID), escritura, actualización y eliminación.
- Incluye un filtro específico para buscar directores por país.
- La base de datos es relacional (PostgreSQL) y está alojada en Neon.

## Configuración de conexión
Por directrices de seguridad, la contraseña de la base de datos no se subió al repositorio público. Para que el proyecto compile y se conecte correctamente:
1. Navegue hasta `src/com/peliculas/connection/DatabaseConnection.java`.
2. Ubique la variable `password`.
3. Reemplace el texto actual por la credencial de acceso suministrada en la entrega.

## Modelo Entidad-Relación

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
