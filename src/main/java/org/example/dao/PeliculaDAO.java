package org.example.dao;

import org.example.JdbcUtil;
import org.example.Pelicula;
import org.example.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO implements DAO<Pelicula>{
    /**
     * Encuentra y devuelve una lista de todas las copias en la base de datos
     * */
    @Override
    public List<Pelicula> findAll() {
        var salida = new ArrayList<Pelicula>();
        try {
            var st = JdbcUtil.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from Peliculas");
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
    public Pelicula findById(int id) {
        return null;
    }

    @Override
    public void save(Pelicula pelicula) throws SQLException {

    }

    @Override
    public void delete(Pelicula pelicula) {

    }

    @Override
    public void update(Pelicula pelicula) {

    }
}
