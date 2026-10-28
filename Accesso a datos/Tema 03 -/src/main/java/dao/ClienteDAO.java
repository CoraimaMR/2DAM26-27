package dao;

import conexion.ConexionBD;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Cliente.
 *
 * Contiene el SQL y transforma filas de la tabla cliente
 * en objetos Cliente, y viceversa.
 */
public class ClienteDAO {

    public List<Cliente> listar() throws SQLException {

        List<Cliente> resultado = new ArrayList<>();

        String sql = """
                SELECT id, nombre, email, activo
                FROM cliente
                ORDER BY id
                """;

        try (
                Connection con = ConexionBD.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                resultado.add(
                        new Cliente(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getBoolean("activo")
                        )
                );

            }
        }
        return resultado;
    }

    public void insertar(Cliente cliente) throws SQLException {
        String sql = """
                INSERT INTO cliente (nombre, email, activo)
                VALUES (?, ?, ?)
        """;

        try (
            Connection con = ConexionBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setBoolean(3, cliente.isActivo());

            ps.executeUpdate();

            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()){
                    cliente.setId(claves.getInt(1));
                }
            }
        }
    }

    public boolean actualizar(Cliente cliente) throws SQLException {
        String sql = """
                UPDATE cliente
                SET nombre = ?, email = ?, activo = ?
                WHERE id = ?
                """;

        try (
                Connection con = ConexionBD.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setBoolean(3 , cliente.isActivo());
            ps.setInt(4 , cliente.getId());

            return ps.executeUpdate() == 1;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = """
                DELETE FROM cliente
                WHERE id = ?
                """;

        try (
                Connection con = ConexionBD.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

}