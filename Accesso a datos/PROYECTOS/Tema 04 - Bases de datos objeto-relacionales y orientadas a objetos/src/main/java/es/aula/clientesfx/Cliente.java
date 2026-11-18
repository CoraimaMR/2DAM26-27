package es.aula.clientesfx;
import java.util.List;
/** JSON se conserva como texto válido que PostgreSQL valida al convertirlo a jsonb. */
public record Cliente(long id, String nombre, String email, Direccion direccion,
                      List<String> telefonos, String preferenciasJson) { }
