package org.example.Views;

import org.example.Copia;
import org.example.JdbcUtil;
import org.example.dao.CopiaDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Detalles extends JFrame {
    private JPanel panel1;
    private JLabel dtitulo;
    private JLabel dgenero;
    private JLabel danio;
    private JLabel ddescripcion;
    private JLabel ddirector;
    private JLabel destado;
    private JLabel dsoporte;
    private JButton volverButton;
    private JButton salirButton;
    private Integer idUser;
    private Integer idCopia;

    /**
     * Con ayuda de los parametros, muestra una pantalla con los detalles de las copias y de la pelicula
     * a la que corresponde
     * @param idUser
     * @param idCopia
     */
    public Detalles(Integer idUser, Integer idCopia) {
        setTitle("Pantalla de detalles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        this.idUser = idUser;
        this.idCopia = idCopia;
        //cargarDetalles(idCopia);
        CopiaDAO dao = new CopiaDAO();
        //dao.cDetalles(idCopia);
        List<String> datos = dao.cDetalles(idCopia);
        if (datos != null) { // titulo, genero, anio, descripcion, director, estado, soporte
            dtitulo.setText(datos.get(0));
            dgenero.setText(datos.get(1));
            danio.setText(String.valueOf(datos.get(2)));
            ddescripcion.setText(datos.get(3));
            ddirector.setText(datos.get(4));
            destado.setText(datos.get(5));
            dsoporte.setText(datos.get(6));
        }

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pprincipal listado = new Pprincipal(idUser);
                listado.setVisible(true);
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


//    public void cargarDetalles(Integer idCopia) {
//        String sql = "SELECT Pelicula.titulo, Pelicula.genero, Pelicula.anio, Pelicula.descripcion, Pelicula.director, " +
//                "copia.estado, copia.soporte " +
//                "FROM copia " +
//                "INNER JOIN Pelicula ON copia.id_pelicula = Pelicula.id " +
//                "WHERE copia.id = ?";
//
//        try (Connection con = JdbcUtil.getConnection()) {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, idCopia);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                dtitulo.setText(rs.getString("titulo"));
//                dgenero.setText(rs.getString("genero"));
//                danio.setText(String.valueOf(rs.getInt("anio")));
//                ddescripcion.setText(rs.getString("descripcion"));
//                ddirector.setText(rs.getString("director"));
//                destado.setText(rs.getString("estado"));
//                dsoporte.setText(rs.getString("soporte"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
