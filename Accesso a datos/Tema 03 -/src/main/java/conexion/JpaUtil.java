package conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("tiendaPU");

    private JpaUtil() {

    }

    public static EntityManager abrir() {
        return FACTORY.createEntityManager();
    }

    public static void cerrar() {
        if (FACTORY.isOpen()) FACTORY.close();
    }
}
