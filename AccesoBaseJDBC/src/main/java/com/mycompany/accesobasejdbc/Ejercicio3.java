/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.accesobasejdbc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Jorge Wang Wang
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ventana ve = new ventana();
       ve.setVisible(true);
    }
    
}
class ventana extends JFrame{
    public ventana(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,1000,400);
        panelo p = new panelo();
        add(p);
    }
}

class panelo extends JPanel{
    public panelo(){
        JMenuBar bar = new JMenuBar();
        
        JMenu menu = new JMenu("Info");
        JMenuItem item1 = new JMenuItem("Tabla Payment");
        JMenuItem item2 = new JMenuItem("Tabla Rental");
        JMenuItem item3 = new JMenuItem("Tabla Store");
        JMenuItem item4 = new JMenuItem("Tabla payment");
        JMenuItem item5 = new JMenuItem("Tabla customer");
        JMenuItem item6 = new JMenuItem("Tabla staff");
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.add(item5);
        menu.add(item6);
        JMenu menu2 = new JMenu("Ejercicio 4 ");
        JMenuItem item8 = new JMenuItem("Info de Ciudades");
        JMenuItem item9 = new JMenuItem("Peliculas por duración");
        JMenuItem item10 = new JMenuItem("Info de Empleado");
        
        menu2.add(item8);
        menu2.add(item9);
        menu2.add(item10);
        
        bar.add(menu2);
        bar.add(menu);
        JPanel arriba = new JPanel(new FlowLayout(FlowLayout.CENTER));
        arriba.add(bar);
        //ImageIcon derecha = new ImageIcon("cascada derecha.jpg");
        //JLabel imagenderecha = new JLabel();
        //imagenderecha.setIcon(derecha);
        
        ImagePanel imagenderecha = new ImagePanel("cascadaizquierda.jpg");
        ImagePanel imagenizquierda = new ImagePanel("cascada derecha.jpg");
        
        setLayout(new BorderLayout());
        add(arriba, BorderLayout.NORTH);
        add(imagenderecha,BorderLayout.EAST);
        add(imagenizquierda,BorderLayout.WEST);
        JTextArea ta = new JTextArea();
        JScrollPane sp = new JScrollPane(ta);
        add(sp,BorderLayout.CENTER);
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "";
        item1.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from payment";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Payment\n";
                while(rs.next()){
                    texto += "ID payment: " + rs.getInt("payment_id")+ ", Fecha Payment: " +rs.getDate("payment_date")+"\n";
                }
                ta.setText(texto);
            } catch (SQLException ex) { 
            }
        });
        item2.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from rental";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Rental\n";
                while(rs.next()){
                    texto += "ID rental: " + rs.getInt("rental_id")+ ", Fecha de vuelta: " +rs.getDate("return_date")+"\n";
                }
                ta.setText(texto); 
            } catch (SQLException ex) {
            }
        });
        item3.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from store";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Store\n";
                while(rs.next()){
                    texto += "ID store: " + rs.getInt("store_id")+ ", Address ID: " +rs.getInt("address_id")+"\n";
                }
                ta.setText(texto); 
            } catch (SQLException ex) {
            }
        });
        item4.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from payment where amount >3";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Payment\n";
                while(rs.next()){
                    texto += "Customer ID: " + rs.getInt("customer_id")+", amount: "+rs.getDouble("amount") +", payment date: " +rs.getDate("payment_date")+"\n";
                }
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
        item5.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from customer where active =true";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Customer\n";
                while(rs.next()){
                    texto += "Customer ID : " + rs.getInt("customer_id")+ ", Primer Nombre: " +rs.getString("first_name")+"\n";
                }
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
        item6.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from staff where active =true";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Staff\n";
                while(rs.next()){
                    texto += "Primer Nombre: " + rs.getString("first_name")+ ", apellido: " +rs.getString("last_name")+", adress ID: "+ rs.getInt("address_id") +", store ID: "+ rs.getInt("store_id")+"\n";
                }
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
        item8.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String pais = JOptionPane.showInputDialog("Pon un pais en ingles");
                String select = "select city from city where country_id = (select country_id from country where country='"+pais+"')";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla ciudades por pais\n";
                while(rs.next()){
                    texto += rs.getString("city")+"\n";
                }
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
        item9.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                int minimo = Integer.parseInt(JOptionPane.showInputDialog("Pon un pais en ingles (minutos)"));
                int maximo = Integer.parseInt(JOptionPane.showInputDialog("Pon un pais en ingles (minutos)"));
                String select = "select * from film where length>"+minimo+" && length<"+maximo;
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Staff\n";
                while(rs.next()){
                    texto += rs.getString("title")+" "+rs.getString("length")+"\n";
                }
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
        item10.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String select = "Select * from staff where active =true";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Staff\n";
                while(rs.next()){
                    texto += "Primer Nombre: " + rs.getString("first_name")+ ", apellido: " +rs.getString("last_name")+", adress ID: "+ rs.getInt("address_id") +", store ID: "+ rs.getInt("store_id")+"\n";
                }
                /*#select * from address inner join staff on  address.address_id=staff.address_id where city_id = (select city_id from city inner join country on country.country_id = city.country_id)
select country from address inner join staff on  address.address_id=staff.address_id inner join city on address.city_id = city.city_id inner join country on country.country_id = city.country_id 
# 3 y 4
#Lethbridge Wooddridge
#Canada Australia*/
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
    }
}
class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String imagePath) {
        // Intenta cargar la imagen
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error aquí
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Aquí puedes definir el tamaño preferido que desees para el panel
        return new Dimension(250, 300); // Ancho grande, por ejemplo 800px
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen escalada al tamaño del panel
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
class ImageBajo extends JPanel {
    private BufferedImage image;

    public ImageBajo(String imagePath) {
        // Intenta cargar la imagen
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error aquí
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Aquí puedes definir el tamaño preferido que desees para el panel
        return new Dimension(700, 100); // Ancho grande, por ejemplo 800px
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen escalada al tamaño del panel
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}