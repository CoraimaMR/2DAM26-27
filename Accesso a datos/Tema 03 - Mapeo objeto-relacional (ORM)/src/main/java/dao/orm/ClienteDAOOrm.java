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

    public void insertar(ClienteEntidad nuevo) {
        TransaccionesORM.ejecutar(em -> {
            em.persist(nuevo);
            return null;
        });
    }

    public boolean actualizar (ClienteEntidad nuevo) {
        return TransaccionesORM.ejecutar(em -> {
            ClienteEntidad actual = em.find(
                    ClienteEntidad.class,
                    nuevo.getId()
            );

            if (actual == null) {
                return false;
            }

            actual.setNombre(nuevo.getNombre());
            actual.setEmail(nuevo.getEmail());
            actual.setActivo(nuevo.isActivo());
            return true;
        });
    }

    public boolean eliminar(int id) {
        return TransaccionesORM.ejecutar(em -> {
           ClienteEntidad actual = em.find(
                   ClienteEntidad.class,
                   id
           );

           if (actual == null) {
               return false;
           }

           em.remove(actual);
           return true;
        });
    }
}
