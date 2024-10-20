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

public class UsuarioDAO implements DAO<Usuario> {

    /**
     * Encuentra y devuelve una lista de todas los usuarios en la base de datos
     * */
    @Override
    public List<Usuario> findAll() {
        var salida = new ArrayList<Usuario>();
        try {
            var st = JdbcUtil.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from usuarios");
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
    public Usuario findById(int id) {
        return null;
    }

    @Override
    public void save(Usuario usuario) throws SQLException {

    }

    @Override
    public void delete(Usuario usuario) {

    }

    @Override
    public void update(Usuario usuario) {

    }

    /* En un principio devolvia un boolean, pero como necesito pasar el id del usuario que se loggea a Pprincipal, voy a hacer
     * que me devuelva el id en forma de int, de manera que si me devuelve un int(id) es que el usuario existe y si me devuelve
     * null es que no existe
     * */
    /**
     * Realiza la comprobacion con la base de datos para saber si el usuario y la contraseña introducidos en
     * los textfield son correctos y se corresponden con un usuario existente, devolvindo el id de este.
     * **/
    public Integer validar(String user, String pass) {
        try (Connection con = JdbcUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM Usuarios WHERE nombre = ? AND contrasena = ?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al validar usuario", e);
        }
    }

    /**
     * Con el id del usuario como parametro consulta con la base de datos todas las copias de peliculas
     * asociadas a este y devuelve una lista con las copias.
     * @param idUser
     * @return
     */
    public List<Copia> cargarP(Integer idUser){
        List<Copia> copias = new ArrayList<>();
        try(Connection con = JdbcUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select Peliculas.titulo, Copias.estado, Copias.soporte, Copias.id from Copias inner join Peliculas on Copias.id_pelicula = Peliculas.id where Copias.id_usuario = ?");
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String titulo = rs.getString("titulo");
                String estado = rs.getString("estado");
                String soporte = rs.getString("soporte");
                Integer id = rs.getInt("id");
                Copia copia = new Copia(titulo, estado, soporte, id);

                copias.add(copia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return copias;
    }



}
