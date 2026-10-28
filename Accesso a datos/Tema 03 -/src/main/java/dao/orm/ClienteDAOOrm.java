package dao.orm;

import conexion.JpaUtil;
import jakarta.persistence.EntityManager;
import modelo.orm.ClienteEntidad;

import java.util.List;

public class ClienteDAOOrm {

    public List<ClienteEntidad> listar() {
        EntityManager em = JpaUtil.abrir();

        try {
            return em.createQuery(
                    "Select c FROM ClienteEntidad c ORDER BY c.id",
                    ClienteEntidad.class
            ).getResultList();
        } finally {
            em.close();
        }
    }
}
