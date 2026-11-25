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
        String sql = SELECT + (ciudad == null ? "" : " WHERE (direccion).ciudad = ?") + " ORDER BY id";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = Conexion.abrir();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (ciudad != null) {
                ps.setString(1, ciudad);
            }
            try (ResultSet resultado = ps.executeQuery()) {
                while (resultado.next()) {
                    Direccion direccion = new Direccion(
                            resultado.getString("calle"),
                            resultado.getString("ciudad"),
                            resultado.getString("cp")
                    );
                    java.sql.Array arraySql = resultado.getArray("telefonos");
                    List<String> telefonos = new ArrayList<>();
                    if (arraySql != null) {
                        try {
                            String[] valores = (String[]) arraySql.getArray();
                            telefonos.addAll(Arrays.asList(valores)
                            );
                        } finally {
                            arraySql.free();
                        }
                    }
                    String preferencias = resultado.getString("preferencias");
                    Cliente cliente = new Cliente(
                            resultado.getLong("id"),
                            resultado.getString("nombre"),
                            resultado.getString("email"),
                            direccion,
                            telefonos,
                            preferencias
                    );
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    @Override public long insertar(Cliente cliente) throws SQLException {
        String sql = """
                INSERT INTO public.cliente (
                 nombre,
                 email,
                 direccion,
                 telefonos,
                 preferencias
                 ) VALUES (
                 ?,
                 ?,
                 ROW(?, ?, ?)::public.direccion_tipo,
                 ?,
                 ?::jsonb
                 )
                 RETURNING id
                """;
        try(Connection conexion = Conexion.abrir();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, cliente.nombre());
            ps.setString(2, cliente.email());
            ps.setString(3, cliente.direccion().calle());
            ps.setString(4, cliente.direccion().ciudad());
            ps.setString(5, cliente.direccion().codigoPostal());
            java.sql.Array arraySql = conexion.createArrayOf( "text", cliente.telefonos().toArray());
            try {
                ps.setArray(6, arraySql);
                ps.setString(7, cliente.preferenciasJson());
                try(ResultSet resultado = ps.executeQuery()) {
                    if(!resultado.next()){
                        throw new SQLException("INSERT no hay identificador");
                    }
                    return resultado.getLong(1);
                }
            } finally {
                arraySql.free();
            }
        }
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
