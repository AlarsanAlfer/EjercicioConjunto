package org.example.dao;

import org.example.Copia;
import org.example.JdbcUtil;
import org.example.Pelicula;
import org.example.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CopiaDAO implements DAO<Copia> {
/**
 * Encuentra y devuelve una lista de todas las copias en la base de datos
 * */

    @Override
    public List<Copia> findAll() {
        var salida = new ArrayList<Copia>();
        try {
            var st = JdbcUtil.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from Copia");
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("id"));
                user.setUser(rs.getString("nombre"));
                user.setPass(rs.getString("contrasena"));
            }
            salida.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public Copia findById(int id) {
        return null;
    }

    @Override
    public void save(Copia copia) throws SQLException {

    }

    @Override
    public void delete(Copia copia) {

    }

    @Override
    public void update(Copia copia) {

    }
    /**
     * Con el id de la copia como parametro, comprueba con la base de datos y muestra todos los datos
     * de la copia y de la pelicula a la que corresponde.
     * **/
    public List<String> cDetalles(Integer idCopia) {
        List<String> datos = new ArrayList<>();
        try (Connection con = JdbcUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT Peliculas.titulo, Peliculas.genero, Peliculas.anio, Peliculas.descripcion, Peliculas.director, " +
                    "Copias.estado, Copias.soporte " +
                    "FROM Copias " +
                    "INNER JOIN Peliculas ON Copias.id_pelicula = Peliculas.id " +
                    "WHERE Copias.id = ?");
            ps.setInt(1, idCopia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datos.add(rs.getString("titulo"));
                datos.add(rs.getString("genero"));
                datos.add(String.valueOf(rs.getInt("anio")));
                datos.add(rs.getString("descripcion"));
                datos.add(rs.getString("director"));
                datos.add(rs.getString("estado"));
                datos.add(rs.getString("soporte"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return datos;

    }
}
