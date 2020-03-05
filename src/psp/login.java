/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;
public class login extends JFrame implements ActionListener {
   JPanel panel;
   JLabel usuario, contrasena, mensaje;
   JTextField rellenarusuario;
   JPasswordField rellenarcontra;
   JButton submit, cancel;
   public static void main(String[] args) {
      new login();
   }
   login() {
      // Username Label
      usuario = new JLabel();
      usuario.setText("Usuario:");
      rellenarusuario = new JTextField();
      // Password Label
      contrasena = new JLabel();
      contrasena.setText("Contraseña:");
      rellenarcontra = new JPasswordField();
      // Submit
      submit = new JButton("Ingresar");
      panel = new JPanel(new GridLayout(3, 1));
      panel.add(usuario);
      panel.add(rellenarusuario);
      panel.add(contrasena);
      panel.add(rellenarcontra);
      mensaje = new JLabel();
      panel.add(mensaje);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle("Por favor introduce un usuario");
      setSize(450,350);
      setVisible(true);
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
      String userName = rellenarusuario.getText();
      String rellenarcontras = rellenarcontra.getText();
      Usuario u = new Usuario();
      u.encriptado(rellenarcontras, rellenarcontras);
      JOptionPane.showMessageDialog(null, "Usuario:"+userName+ "Contraseña:"+ u.encriptado(rellenarcontras, rellenarcontras));
      
      if (userName.trim().equals("admin") && rellenarcontras.trim().equals("admin")) {
         Ventana v = new Ventana();
      } else {
         mensaje.setText(" Usuario incorrecto ");
      }
   }
}