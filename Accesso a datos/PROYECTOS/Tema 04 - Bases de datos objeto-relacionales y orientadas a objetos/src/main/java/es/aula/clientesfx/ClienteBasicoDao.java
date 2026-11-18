package es.aula.clientesfx;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/** REPASO RESUELTO: CRUD relacional parametrizado, sin arrays ni compuestos. */
public final class ClienteBasicoDao {
    public List<ClienteBasico> listar(String ciudad) throws SQLException {
        String sql = "SELECT id,nombre,email,ciudad FROM public.cliente_repaso";
        boolean filtrar = ciudad != null && !ciudad.isBlank();
        if (filtrar) sql += " WHERE ciudad = ?";
        sql += " ORDER BY id";
        List<ClienteBasico> clientes = new ArrayList<>();
        try (Connection c = Conexion.abrir(); PreparedStatement ps = c.prepareStatement(sql)) {
            if (filtrar) ps.setString(1, ciudad);
            try (ResultSet rs = ps.executeQuery()) {
                // El cursor empieza antes de la primera fila.
                while (rs.next()) clientes.add(new ClienteBasico(rs.getLong("id"),
                    rs.getString("nombre"), rs.getString("email"), rs.getString("ciudad")));
            }
        }
        return clientes;
    }
    public long insertar(ClienteBasico cliente) throws SQLException {
        String sql = "INSERT INTO public.cliente_repaso(nombre,email,ciudad) VALUES (?,?,?) RETURNING id";
        try (Connection c = Conexion.abrir(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cliente.nombre()); ps.setString(2, cliente.email()); ps.setString(3, cliente.ciudad());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new SQLException("No se devolvió el id");
                return rs.getLong(1);
            }
        }
    }
    public boolean actualizar(ClienteBasico cliente) throws SQLException {
        String sql = "UPDATE public.cliente_repaso SET nombre=?,email=?,ciudad=? WHERE id=?";
        try (Connection c = Conexion.abrir(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cliente.nombre()); ps.setString(2, cliente.email());
            ps.setString(3, cliente.ciudad()); ps.setLong(4, cliente.id());
            return ps.executeUpdate() == 1;
        }
    }
    public boolean eliminar(long id) throws SQLException {
        try (Connection c = Conexion.abrir(); PreparedStatement ps = c.prepareStatement(
                "DELETE FROM public.cliente_repaso WHERE id=?")) {
            ps.setLong(1, id); return ps.executeUpdate() == 1;
        }
    }
}
