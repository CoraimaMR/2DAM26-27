package app;

import conexion.JpaUtil;
import jakarta.persistence.EntityManager;
import modelo.orm.ClienteEntidad;

import java.util.List;

public class PruebaConsultaClientesORM {

    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = JpaUtil.abrir();

            List<ClienteEntidad> clientes = em.createQuery(
                    "SELECT c FROM ClienteEntidad c ORDER BY c.id",
                    ClienteEntidad.class
            ).getResultList();

            for(ClienteEntidad cliente : clientes) {
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Activo: " + cliente.isActivo());
            }
        } catch (Exception e) {
            System.err.println("Error al obtener clientes");
        } finally {
            if ( em != null && em.isOpen()){
                em.close();
            }

            try {
                JpaUtil.cerrar();
            } catch (Exception | LinkageError e) {
                System.err.println("No se pudo cerrar la Factoría JPA");
                e.getMessage();
            }
        }

    }

}