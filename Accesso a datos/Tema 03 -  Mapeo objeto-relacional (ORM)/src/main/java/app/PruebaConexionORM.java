package app;

import conexion.JpaUtil;
import jakarta.persistence.EntityManager;

public class PruebaConexionORM {
    public static void main(String[] args) {
        System.out.println("Prueba de conexión");

        EntityManager em = null;

        try {
            em = JpaUtil.abrir();
            System.out.println("Conexión creada correctamente");

            Object resultado = em.createNativeQuery("SELECT 1").getSingleResult();
            System.out.println("Resultado: " +  resultado);

            if ( ((Number)resultado).intValue() == 1 ){
                System.out.println("CONEXIÓN Y CONSULTA CORRECTAS");
            }
        } catch (Exception e ){
            System.err.println("ERROR EN LA PRUEBA ORM: ");
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

            try {
                JpaUtil.cerrar();
            } catch (Exception | LinkageError e) {
                System.err.println("ERROR.No se pudo cerrar la factoría JPA: ");
                e.getMessage();
            }
        }
    }
}
