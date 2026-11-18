package es.aula.clientesfx;
import java.util.Arrays;
import java.util.List;
/** Validación de entrada compartida. PostgreSQL impone además sus restricciones. */
public final class Validacion {
    private Validacion() { }
    public static String obligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + ": introduce un valor.");
        return valor.trim();
    }
    public static String email(String valor) {
        String s = obligatorio(valor, "Email");
        if (!s.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+"))
            throw new IllegalArgumentException("El email debe tener un formato como nombre@dominio.es.");
        return s;
    }
    public static String cp(String valor) {
        String s = obligatorio(valor, "Código postal");
        if (!s.matches("[0-9]{5}")) throw new IllegalArgumentException("El código postal debe tener cinco cifras.");
        return s;
    }
    public static List<String> telefonos(String valor) {
        if (valor == null || valor.isBlank()) return List.of();
        List<String> lista = Arrays.stream(valor.split(";", -1)).map(String::trim).toList();
        if (lista.stream().anyMatch(String::isBlank))
            throw new IllegalArgumentException("Separa los teléfonos con ; y evita elementos vacíos.");
        return lista;
    }
}
