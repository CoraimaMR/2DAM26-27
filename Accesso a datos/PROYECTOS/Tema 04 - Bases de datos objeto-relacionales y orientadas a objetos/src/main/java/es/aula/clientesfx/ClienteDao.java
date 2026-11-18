package es.aula.clientesfx;
import java.sql.*;
import java.util.*;
/** SQL y particularidades del gestor quedan aislados de la interfaz gráfica. */
public final class ClienteDao implements ClienteRepository {

    private static final String SELECT = """
        SELECT id,nombre,email,(direccion).calle AS calle,
               (direccion).ciudad AS ciudad,(direccion).codigo_postal AS cp,
               telefonos,preferencias::text AS preferencias
        FROM public.cliente
        """;

    @Override public List<Cliente> listar(String ciudad) throws SQLException {
        //???????????
    }

    @Override public long insertar(Cliente cliente) throws SQLException {
        // TODO 2: construir ROW y enviar java.sql.Array
        throw new SQLException("Pendiente de implementar: insertar (paso 2)", "0A000");
    }

    @Override public boolean actualizarContacto(long id,String email,List<String> telefonos) throws SQLException {
        // TODO 3: actualizar email y enviar el array de teléfonos
        throw new SQLException("Pendiente de implementar: actualizarContacto (paso 3)", "0A000");
    }

    @Override public boolean eliminar(long id) throws SQLException {
        try (Connection c=Conexion.abrir(); PreparedStatement ps=c.prepareStatement("DELETE FROM public.cliente WHERE id=?")) {
            ps.setLong(1,id); return ps.executeUpdate()==1;
        }
    }

    @Override public void cambiarCiudad(long id,String ciudad) throws SQLException {
        // TODO 4: llamar al procedimiento con CallableStatement
        throw new SQLException("Pendiente de implementar: cambiarCiudad (paso 4)", "0A000");
    }

    @Override public boolean cambiarPreferencias(long id,String json) throws SQLException {
        // TODO 5: enviar JSON con cast a jsonb
        throw new SQLException("Pendiente de implementar: cambiarPreferencias (paso 5)", "0A000");
    }

    @Override public long total() throws SQLException {
        // TODO 6: registrar y recuperar el parámetro de salida de la función
        throw new SQLException("Pendiente de implementar: total (paso 6)", "0A000");
    }
}
