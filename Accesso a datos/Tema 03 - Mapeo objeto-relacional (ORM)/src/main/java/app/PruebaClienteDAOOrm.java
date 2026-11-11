package app;

import conexion.JpaUtil;
import dao.orm.ClienteDAOOrm;
import modelo.orm.ClienteEntidad;

import java.util.List;

public class PruebaClienteDAOOrm {
    public static void main(String[] args) {

        try {
            ClienteDAOOrm dao = new ClienteDAOOrm();

            List<ClienteEntidad> clientes = dao.listar();

            System.out.println("Número de clientes: "  + clientes.size());

            for (ClienteEntidad cliente : clientes){
                System.out.println(cliente.getId() + " - " +
                        cliente.getNombre() + "- "
                        + cliente.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Error al obtener clientes");
        } finally {

            try {
                JpaUtil.cerrar();
            } catch (Exception | LinkageError e) {
                System.err.println("No se pudo cerrar la Factoría JPA");
                e.getMessage();
            }
        }
    }
}