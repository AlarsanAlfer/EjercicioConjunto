package org.example;

import lombok.Data;

/**
 * Clase que representa las copias de las peliculas que tienen los usuarios
 */
@Data
public class Copia{
    private String titulo;
    private String estado;
    private String soporte;
    private Integer id;


    public Copia(String titulo, String estado, String soporte, Integer id) {
        this.titulo = titulo;
        this.estado = estado;
        this.soporte = soporte;
        this.id = id;
    }

    public Copia() {
    }

    public String toString() {
        return titulo + " - " + estado + " - " + soporte;
    }

}
