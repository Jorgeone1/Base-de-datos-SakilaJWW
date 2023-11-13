/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.accesobasejdbc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author Alumno
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setBounds(100, 100, 500, 300);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea ta = new JTextArea();
        JScrollPane sp = new JScrollPane(ta);
        panel.add(sp, BorderLayout.CENTER);

        JPanel norte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel jl = new JLabel("Ejercicio 2");
        norte.add(jl);
        panel.add(norte, BorderLayout.NORTH);
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton crear = new JButton("Crear Base de datos empleado");
        JButton mostrar = new JButton("Mostrar Datos");
        sur.add(crear);
        sur.add(mostrar);
        panel.add(sur, BorderLayout.SOUTH);
        ventana.add(panel);

        ventana.setVisible(true);
        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/test";
                String username = "root";
                String password = "";
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    String tabla = "create table empleado(\n"
                            + "id int(4),\n"
                            + "edad int(4),\n"
                            + "nombre VARCHAR(20),\n"
                            + "apellido VARCHAR(20)\n"
                            + ");";
                    Statement stm = connection.createStatement();
                    stm.executeUpdate(tabla);
                    
                    String[] nombre = {"Zaa","Mazdaa","Zeit","Summit"};
                    String[] apellido = {"Ali","Fatma","Kadma","Mital"};
                    int[] id = {100,101,102,103};
                    int[] edad = {18,25,30,22};
                    for (int i = 0; i < 4; i++) {
                        stm.executeUpdate("Insert into Empleado(id,edad,apellido,nombre) VALUES('"+id[i]+"','"+edad[i]+"','"+nombre[i]+"','"+apellido[i]+"')");
                    }
                    // Consulta para mostrar el contenido de la tabla 'cit
                    ta.setText("Se creo la base de datos EMPC");
                    connection.close();
                } catch(SQLSyntaxErrorException ex){
                    ta.setText("La tabla esta ya creada");
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });
        mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/test";
                String username = "root";
                String password = "";
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    String select = "SELECT * FROM empleado";
                    Statement stm = connection.createStatement();
                    ResultSet rs = stm.executeQuery(select);
                    String texto="";
                    while(rs.next()){
                        texto +="id: "+rs.getInt("id")+" nombre: "+ rs.getString("nombre")+" apellido: "+rs.getString("apellido")+ " edad: " + rs.getInt("edad") +"\n";
                    }
                    
                    ta.setText(texto);
                    // Consulta para mostrar el contenido de la tabla 'cit
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

}
