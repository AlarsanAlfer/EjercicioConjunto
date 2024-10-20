package org.example;

import lombok.Data;

/**
 * Clase que representa a los diferentes usuarios que hay en la BD.
 */
@Data
public class Usuario {
    private Integer idUsuario;
    private String user;
    private String pass;

    public Usuario(Integer idUsuario, String user, String pass) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.pass = pass;
    }

    public Usuario() {}
}
