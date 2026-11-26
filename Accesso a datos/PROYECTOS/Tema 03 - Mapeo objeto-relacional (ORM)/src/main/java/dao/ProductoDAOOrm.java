package dao;

import conexion.JpaUtil;
import jakarta.persistence.EntityManager;
import modelo.ProductoEntidad;

import java.util.List;

public class ProductoDAOOrm {

    public List<ProductoEntidad> listar() {
        EntityManager em = JpaUtil.abrir();

        try {
            return em.createQuery(
                    "Select p FROM ProductoEntidad p ORDER BY p.id",
                    ProductoEntidad.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public void insertar(ProductoEntidad nuevo) {
        TransaccionesORM.ejecutar(em -> {
            em.persist(nuevo);
            return null;
        });
    }

    public boolean actualizar (ProductoEntidad nuevo) {
        return TransaccionesORM.ejecutar(em -> {
            ProductoEntidad actual = em.find(
                    ProductoEntidad.class,
                    nuevo.getId()
            );

            if (actual == null) {
                return false;
            }

            actual.setNombre(nuevo.getNombre());
            actual.setPrecio(nuevo.getPrecio());
            actual.setStock(nuevo.getStock());
            return true;
        });
    }

    public boolean eliminar(int id) {
        return TransaccionesORM.ejecutar(em -> {
            ProductoEntidad actual = em.find(
                    ProductoEntidad.class,
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
