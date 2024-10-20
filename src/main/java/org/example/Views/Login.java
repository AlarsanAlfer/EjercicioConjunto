package org.example.Views;

import org.example.JdbcUtil;
import org.example.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JFrame {
    private JPanel panel1;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel ElPanel;
    private JLabel error;

    /**
     * Muesta una pantalla de login, donde comprueba el usuario y la contraseña
     */
    public Login() {
        setTitle("Pantalla de inicio de sesión");
        setContentPane(ElPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 400);

        getRootPane().setDefaultButton(aceptarButton);

        UsuarioDAO usuarioDAO = new UsuarioDAO();


        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1.getText();
                String contrasena = String.valueOf(passwordField1.getPassword());

                Integer idUser = usuarioDAO.validar(nombre, contrasena);

                if (idUser != null) {
                    Pprincipal listado = new Pprincipal(idUser);
                    listado.setVisible(true);
                    dispose();
                } else {
                    textField1.setText("");
                    passwordField1.setText("");
                    error.setText("El usuario o la contraseña no coincide");
                    error.setVisible(true);
                }

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }



}
