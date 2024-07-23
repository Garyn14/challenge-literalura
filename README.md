# Librería Alura

## Descripción

En este emocionante desafío de programación, hemos desarrollado LiterAlura, un catálogo de libros interactivo que permite a los usuarios realizar búsquedas y gestionar información de libros a través de una API específica. Este proyecto enseña cómo realizar solicitudes a una API de libros, manipular datos JSON, almacenarlos en una base de datos y, finalmente, filtrar y mostrar libros y autores de interés.

## Objetivo

Desarrollar un catálogo de libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. Los libros se buscan a través de la API de Gutendex. La información se almacena en una base de datos PostgreSQL usando JPA, y se utiliza Flyway para las migraciones.

## Opciones de Interacción

La consola de LiterAlura ofrece las siguientes opciones:

1. **Búsqueda de libro por título**: Busca un determinado libro en la API de Gutendex y mapea el JSON a una clase en Java usando Jackson. Luego, se inserta en la base de datos PostgreSQL.
2. **Listar libros**: Muestra una lista de libros almacenados en la base de datos.
3. **Listar autores**: Muestra una lista de autores almacenados en la base de datos.
4. **Autores vivos en determinado año**: Filtra y muestra autores que estaban vivos en un año específico.
5. **Estadísticas por lenguaje**: Muestra estadísticas de libros agrupadas por lenguaje.
6. **Estadísticas de descargas**: Muestra estadísticas de descargas de libros.
7. **Top 5 libros más descargados**: Muestra los cinco libros más descargados.
8. **Buscar autor por nombre**: Busca y muestra información de un autor por su nombre.
0. **Salir**: Sale del programa.

## Instalación

Sigue estos pasos para configurar y ejecutar el proyecto:

1. **Clonar el repositorio**
    ```bash
    git clone https://github.com/Garyn14/challenge-literalura.git
    ```

2. **Configurar la base de datos**
    - Asegúrate de tener PostgreSQL instalado y ejecutándose.
    - Crea una base de datos para el proyecto.
    - Actualiza las credenciales de la base de datos en `application.properties`.

3. **Instalar dependencias**
    ```bash
    mvn install
    ```

## Créditos

Este proyecto fue inspirado por el desafío de Alura y utiliza las siguientes tecnologías:

- Spring Boot
- Spring Data
- Lombok
- [Gutendex API](https://gutendex.com/)
- Jackson
- PostgreSQL
- JPA
- Flyway
