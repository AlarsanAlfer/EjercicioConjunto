package org.example;

/**
 * Clase que representa las diferentes peliculas.
 */

public class Pelicula {
    private Integer idPelicula;
    private String titulo;
    private String genero;
    private Integer anio;
    private String descripcion;
    private String director;

    public Pelicula(Integer idPelicula, String titulo, Integer anio, String genero, String descripcion, String director) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.anio = anio;
        this.genero = genero;
        this.descripcion = descripcion;
        this.director = director;
    }
}
