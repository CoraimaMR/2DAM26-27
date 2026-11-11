package dao;

import conexion.ConexionBD;
import modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listar() throws SQLException {

        List<Producto> resultado = new ArrayList<>();

        String sql = """
                SELECT id, nombre, precio , stock
                FROM producto
                ORDER BY id
                """;

        try (
                Connection con = ConexionBD.obtenerConexion(); // ABRE BDD
                PreparedStatement ps = con.prepareStatement(sql); // PREPARA LA BDD
                ResultSet rs = ps.executeQuery() // EJECUTA EL "sql"
                // ResultSet contiene las filas ejecutadas por executeQuery
        ) {
            while (rs.next()) {
                resultado.add(
                        new Producto(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getBigDecimal("precio"),
                                rs.getInt("stock")
                        )
                );

            }
        } // EL TRY CIERRA LA BDD AUTOMÁTICAMENTE
        return resultado;
    }

    public void insertar(Producto producto) throws SQLException {
        String sql = """
                INSERT INTO producto (nombre, precio , stock)
                VALUES (?, ?, ?)
        """;

        try (
                Connection con = ConexionBD.obtenerConexion(); // ABRE BDD
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // PREPARA LA BDD Y SOLICITA EL ID
        ){
            // ASIGNA LOS VALORES A LOS PARÁMETROS "?"
            ps.setString(1, producto.getNombre());
            ps.setBigDecimal(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());

            ps.executeUpdate(); // EJECUTA EL "sql"

            try (ResultSet claves = ps.getGeneratedKeys()) { // ResultSet contiene la clave recién creada
                if (claves.next()){ // Si existe esa clave
                    producto.setId(claves.getInt(1)); // Se asigna
                }
            }
        } // EL TRY CIERRA LA BDD AUTOMÁTICAMENTE
    }

    public boolean actualizar(Producto producto) throws SQLException {
        String sql = """
                UPDATE producto
                SET nombre = ?, precio = ?, stock = ?
                WHERE id = ?
                """;

        try (
                Connection con = ConexionBD.obtenerConexion(); // ABRE LA BDD
                PreparedStatement ps = con.prepareStatement(sql); // PREPARA LA BDD
        ){
            // ASIGNA LOS VALORES A LOS PARÁMETROS "?"
            ps.setString(1, producto.getNombre());
            ps.setBigDecimal(2, producto.getPrecio());
            ps.setInt(3 , producto.getStock());
            ps.setInt(4 , producto.getId());

            return ps.executeUpdate() == 1; // EJECUTA EL "sql" y devuelve true si afectó exactamente una fila
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = """
                DELETE FROM producto
                WHERE id = ?
                """;

        try (
                Connection con = ConexionBD.obtenerConexion(); // ABRE LA BDD
                PreparedStatement ps = con.prepareStatement(sql); // PREPARA LA BDD
        ){
            // ASIGNA LOS VALORES A LOS PARÁMETROS "?"
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;  // EJECUTA EL "sql" y devuelve true si afectó exactamente una fila
        }
    }

}
