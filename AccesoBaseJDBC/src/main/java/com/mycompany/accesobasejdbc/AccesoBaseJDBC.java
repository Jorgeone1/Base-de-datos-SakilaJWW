/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.accesobasejdbc;

import java.sql.*;

/**
 *
 * @author Alumno
 */
public class AccesoBaseJDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "";
         try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // Consulta para mostrar el contenido de la tabla 'city'
            String queryCity = "SELECT * FROM city";
            PreparedStatement cityStatement = connection.prepareStatement(queryCity);
            ResultSet cityResult = cityStatement.executeQuery();

            System.out.println("Contenido de la tabla 'city':");
            while (cityResult.next()) {
                System.out.println(cityResult.getString("city_id") + ", " + cityResult.getString("city"));
            }

            // Consulta para mostrar el contenido de la tabla 'country'
            String queryCountry = "SELECT * FROM country";
            PreparedStatement countryStatement = connection.prepareStatement(queryCountry);
            ResultSet countryResult = countryStatement.executeQuery();
            
            System.out.println("\nContenido de la tabla 'country':");
            while (countryResult.next()) {
                System.out.println(countryResult.getString("country_id") + ", " + countryResult.getString("country"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
