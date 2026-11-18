package es.aula.clientesfx;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public final class Conexion {
    private Conexion() {

    }

    public static Connection abrir() throws SQLException {
        Properties p = new Properties();
        try (InputStream in = Conexion.class.getResourceAsStream("/db.properties")) {
            if (in == null) throw new SQLException("No se encuentra db.properties");
            p.load(in);
        } catch (IOException e) { throw new SQLException("No se puede leer la configuración", e); }
        String url = System.getenv().getOrDefault("DB_URL", p.getProperty("url"));
        Properties credenciales = new Properties();
        credenciales.setProperty("user", System.getenv().getOrDefault("DB_USER", p.getProperty("user")));
        credenciales.setProperty("password", System.getenv().getOrDefault("DB_PASSWORD", p.getProperty("password")));
        // Sin retorno => CALL procedimiento. Con retorno => SELECT función.
        credenciales.setProperty("escapeSyntaxCallMode", "callIfNoReturn");
        credenciales.setProperty("connectTimeout", "5");
        credenciales.setProperty("socketTimeout", "15");
        return DriverManager.getConnection(url, credenciales);
    }
}
