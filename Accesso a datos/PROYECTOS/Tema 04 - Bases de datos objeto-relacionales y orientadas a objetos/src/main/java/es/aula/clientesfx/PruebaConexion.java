package es.aula.clientesfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        try (Connection con = Conexion.abrir()) {
            System.out.println("CONEXION ESTABLECIDA");

            String sql = """
                    SELECT
                    current_database() AS base,
                    current_user AS user
                    """;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String base = rs.getString("base");
                    String usuario = rs.getString("user");

                    System.out.println("Base de datos: " + base);
                    System.out.println("Usuario: " + usuario);
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR JDBC: " + e.getMessage());
            System.err.println("ERROR JDBC: " + e.getSQLState()); // Acciones hechas
            e.printStackTrace(); // Estado de la consulta
        }
    }
}
