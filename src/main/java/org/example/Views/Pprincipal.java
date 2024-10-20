package org.example.Views;

import org.example.Copia;
import org.example.JdbcUtil;
import org.example.dao.UsuarioDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pprincipal extends JFrame {
    private JList<Copia> list1;
    private JPanel panel1;
    private JButton cerrarSesionButton;
    private JButton salirButton;

    private DefaultListModel<Copia> listModel;
    private Integer idUser;
    private Integer idCopia;

    /**
     * Muestra el listado de copias que pertenecen al usuario que ha iniciado sesión
     * @param idUser
     */
    public Pprincipal(Integer idUser) {
        setTitle("Pantalla Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(panel1);
        setLocationRelativeTo(null);

        this.idUser = idUser;
        UsuarioDAO dao = new UsuarioDAO();

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);


        listModel.addAll(dao.cargarP(idUser));


        salirButton.addActionListener(e -> {
            System.exit(0);
        });
        cerrarSesionButton.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        });
        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                Copia copiaSeleccionada = list1.getSelectedValue();
                if (copiaSeleccionada != null) {
                    idCopia = copiaSeleccionada.getId();
                }
            }
            if (idCopia != null) {
                Detalles detalles = new Detalles(idUser, idCopia);
                detalles.setVisible(true);
                dispose();
            }
        });
    }

}
