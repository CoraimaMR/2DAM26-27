package dao.orm;

import conexion.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.function.Function;

public final class TransaccionesORM {

    private TransaccionesORM() {

    } // CONSTRUCTOR

    public static <T> T ejecutar(
            Function<EntityManager, T> accion) {EntityManager em = JpaUtil.abrir();

        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                T resultado = accion.apply(em);
                tx.commit();
                return resultado;

            } catch (RuntimeException ex) {
                if (tx.isActive()) {
                    try {
                        tx.rollback();
                    } catch (RuntimeException errorRollback) {
                        ex.addSuppressed(errorRollback);
                    }
                }
                throw ex;
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
