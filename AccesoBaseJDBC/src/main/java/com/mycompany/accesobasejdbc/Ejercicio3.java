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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setBounds((int)(width/4),(int)(height/4),1000,400);
        panelo p = new panelo();
        add(p);
    }
}

class panelo extends JPanel{
     private int i = 0;
     private static Clip currentClip;
    public panelo(){
        File directorio = new File("musica");
        File[] f = directorio.listFiles();
        System.out.println(f.length);
        playSound(f[i]);
        System.out.println();
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
        
        Button izquierda = new Button("->");
        Button derecha = new Button("<-");
        setLayout(new BorderLayout());
        JPanel panelizquierda = new JPanel();
        panelizquierda.add(imagenderecha);
        panelizquierda.add(izquierda);
        JPanel panelderecha = new JPanel();
        panelderecha.add(derecha);
        panelderecha.add(imagenizquierda);
        
        add(arriba, BorderLayout.NORTH);
        add(panelizquierda,BorderLayout.EAST);
        add(panelderecha,BorderLayout.WEST);
        JTextArea ta = new JTextArea();
        JScrollPane sp = new JScrollPane(ta);
        add(sp,BorderLayout.CENTER);
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "";
        
        izquierda.addActionListener((ActionEvent e)->{
            i++;
            
            if(i==f.length){
                i = 0;
            }
            System.out.println(i);
            playSound(f[i]);
        
    });
        derecha.addActionListener((ActionEvent e)->{
            i--;
           
            if(i==-1){
                i=12;
            }
             System.out.println(i);
        playSound(f[i]);
    });
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
                if("Tabla ciudades por pais\n".equals(texto)){
                    texto="Error no hay datos de ese pais";
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
                String texto="Tabla Pelicula\n";
                while(rs.next()){
                    texto += rs.getString("title")+" "+rs.getString("length")+"\n";
                }
                if("Tabla Pelicula\n".equals(texto)){
                    texto="Error no hay Peliculas entre esa duración";
                }
                ta.setText(texto); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error solo acepta números");
            }
        });
        item10.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String ciudad = JOptionPane.showInputDialog("Pon la ciudad en ingles");
                String pais = JOptionPane.showInputDialog("Pon el pais en ingles");
                String select = "Select * from staff  where address_id = (select address.address_id from address inner join staff on  address.address_id=staff.address_id inner join city on address.city_id = city.city_id inner join country on country.country_id = city.country_id where city.city = '"+ciudad+"' and country.country = '"+pais+"')";
                Statement stm = connection.createStatement();
                ResultSet rs= stm.executeQuery(select);
                String texto="Tabla Staff\n";
                while(rs.next()){
                    texto += "Primer Nombre: " + rs.getString("first_name")+ ", apellido: " +rs.getString("last_name")+", adress ID: "+ rs.getInt("address_id") +", store ID: "+ rs.getInt("store_id")+"\n";
                }
                if("Tabla Staff\n".equals(texto)){
                    texto="Error no hay empleados de esa ciudad";
                }
                /*#select * from address inner join staff on  address.address_id=staff.address_id where city_id = (select city_id from city inner join country on country.country_id = city.country_id)
select country from address inner join staff on  address.address_id=staff.address_id inner join city on address.city_id = city.city_id inner join country on country.country_id = city.country_id 
# 3 y 4
#Lethbridge, 'Woodridge', '

#Canada Australia*/
                ta.setText(texto); 
            } catch (Exception ex) {
            }
        });
        
    
    }
    public static void playSound(File soundFile) {
        try {
            // Abrir un archivo de audio
           if (currentClip != null) {
                currentClip.stop();
                currentClip.close();
            }

            // Abrir un archivo de audio
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Obtener un clip de sonido y cargarlo con el audio
            currentClip = AudioSystem.getClip();
            currentClip.open(audioIn);

            // Reproducir el audio
            currentClip.start();
            // Opcional: esperar a que el audio termine antes de cerrar la aplicación
            // Thread.sleep(clip.getMicrosecondLength() / 1000);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } /* catch (InterruptedException e) { // Descomenta si usas Thread.sleep
            e.printStackTrace();
        } */
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