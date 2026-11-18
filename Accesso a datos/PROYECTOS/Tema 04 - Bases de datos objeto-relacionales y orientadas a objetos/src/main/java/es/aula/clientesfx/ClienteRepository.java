package es.aula.clientesfx;
import java.sql.SQLException;
import java.util.List;
public interface ClienteRepository {
    List<Cliente> listar(String ciudad) throws SQLException;
    long insertar(Cliente cliente) throws SQLException;
    boolean actualizarContacto(long id, String email, List<String> telefonos) throws SQLException;
    boolean eliminar(long id) throws SQLException;
    void cambiarCiudad(long id, String ciudad) throws SQLException;
    boolean cambiarPreferencias(long id, String json) throws SQLException;
    long total() throws SQLException;
}
