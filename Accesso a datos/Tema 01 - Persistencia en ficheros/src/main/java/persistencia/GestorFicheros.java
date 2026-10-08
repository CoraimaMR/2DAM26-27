package persistencia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import modelo.Cliente;
import modelo.Producto;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    private static final XmlMapper XML_MAPPER = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final ObjectMapper MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    // --------------------- CLIENTES ---------------------

    // TXT
    public static void exportarClientesTxt (Path ruta, List<Cliente> clientes) throws IOException {
        try(BufferedWriter bw = Files.newBufferedWriter(ruta,  StandardCharsets.UTF_8)){
            for(Cliente c : clientes) {
                bw.write(c.getId() + ";" + c.getNombre()  + ";" + c.getEmail()  + ";"  + c.getTelefono());
                bw.newLine();
            }
        }
    }

    public static List<Cliente> importarClientesTxt(Path ruta) throws IOException {
        List<Cliente> resultado = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null){
                String[] p = linea.split(";", -1);

                if (p.length != 4 ){
                    continue;
                }

                try {
                    int id = Integer.parseInt(p[0]);
                    String nombre = p[1];
                    String email = p[2];
                    String telefono = p[3];

                    Cliente cliente = new Cliente (id, nombre, email, telefono);
                    resultado.add(cliente);

                } catch (NumberFormatException ex) {
                    System.err.println("Cliente incorrecto: " + linea);
                }
            }
        }
        return resultado;
    }

    // CSV
    public static void exportarClientesCsv(Path ruta, List<Cliente> clientes) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            bw.write("id,nombre,email,telefono");
            bw.newLine();
            for (Cliente c : clientes) {
                bw.write(c.getId() + "," + csv(c.getNombre()) + "," + csv(c.getEmail()) + "," + csv(c.getTelefono()));
            }
        }

    }

    public static List<Cliente> importarClientesCsv(Path ruta) throws IOException {
        List<Cliente> resultado = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader( ruta, StandardCharsets.UTF_8)) {
            String linea = br.readLine();

            while ((linea = br.readLine()) != null) {
                List<String> c = parseCsv(linea);

                if (c.size() != 4) {
                    continue;
                }

                try {
                    resultado.add (new Cliente (Integer.parseInt(c.get(0)), c.get(1), c.get(2), c.get(3)));
                } catch (NumberFormatException e){
                    System.err.println("Cliente errónea: " + linea);
                }

            }
        }
        return resultado;
    }

    // XML
    public static void exportarClientesXml (Path ruta, List<Cliente> clientes) throws IOException {
        XML_MAPPER.writeValue(ruta.toFile(), new ClientesXml(clientes));
    }

    public static class ClientesXml { // RAPER
        private List<Cliente> clientes = new ArrayList<>();

        public ClientesXml(){

        }

        public ClientesXml(List<Cliente> cliente){
            this.clientes = cliente;
        }

        public List<Cliente> getClientes() {return clientes;}

        public void setClientes(List<Cliente> clientes) {
            this.clientes = clientes;
        }
    }

    public static List<Cliente> importarClientesXml (Path ruta) throws IOException {
        ClientesXml contenedor = XML_MAPPER.readValue(ruta.toFile(), ClientesXml.class);

        return contenedor.getClientes() == null ? new ArrayList<>() : contenedor.getClientes();
    }

    // JSON
    public static void exportarClientesJson (Path ruta, List<Cliente> clientes) throws IOException {
        MAPPER.writeValue(ruta.toFile(), clientes);
    }

    public static List<Cliente> importarClientesJson (Path ruta) throws IOException {
        return MAPPER.readValue(ruta.toFile(), new TypeReference<List<Cliente>>(){});
    }

// --------------------- PRODUCTOS ---------------------

    public static void exportarProductosTxt (Path ruta, List<Producto> productos) throws IOException {
        try(BufferedWriter bw = Files.newBufferedWriter(ruta,  StandardCharsets.UTF_8)){
            for(Producto p : productos) {
                bw.write(p.getId() + "," + p.getNombre()  + "," + p.getPrecio()  + ","  + p.getStock());
                bw.newLine();
            }
        }
    }

    public static List<Producto> importarProductosTxt(Path ruta) throws IOException{
        List<Producto> resultado = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null){
                String[] p = linea.split(",", -1);

                if (p.length != 4 ){
                    continue;
                }

                try {
                    int id = Integer.parseInt(p[0]);
                    String nombre = p[1];
                    double precio = Double.parseDouble(p[2]);
                    int stock = Integer.parseInt(p[3]);

                    Producto producto = new Producto (id, nombre, precio, stock);
                    resultado.add(producto);

                } catch (NumberFormatException ex) {
                    System.err.println("Producto incorrecto: " + linea);
                }
            }
        }
        return resultado;
    }

    public static void exportarProductosCsv (Path ruta, List<Producto> productos) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            bw.write("id,nombre,precio,stock");
            bw.newLine();
            for (Producto p : productos) {
                bw.write(p.getId() + "," + p.getNombre()  + "," + p.getPrecio()  + ","  + p.getStock());
                bw.newLine();
            }
        }
    }

    public static List<Producto> importarProductosCsv (Path ruta) throws IOException {
        List <Producto> resultado = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea = br.readLine();

            while ((linea = br.readLine()) != null) {
                List<String> p = parseCsv(linea);

                if (p.size() != 4) {
                    continue;
                }

                try {
                    resultado.add (new Producto (Integer.parseInt(p.get(0)), p.get(1),Double.parseDouble(p.get(2)), Integer.parseInt(p.get(3))));
                } catch (NumberFormatException e){
                    System.err.println("Producto errónea: " + linea);
                }

            }
        }
        return resultado;
    }

    // --------------------- FUNCIONES PRIV ---------------------

    // Entrada: "  Tienda, "Pepe""
    private static String csv(String valor){
        if (valor == null) {
            return "";
        }

        if (valor.contains(",") || valor.contains("\"") || valor.contains("\n")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }

    // Entrada: 3, "Pérez, ""Juan""", Ourense, Pepa
    private static List<String> parseCsv(String linea) {
        List<String> campos = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean entreComillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (c == '"') {
                if (entreComillas && i+1 < linea.length() && linea.charAt(i+1) == '"') {
                    actual.append('"');
                    i++;
                } else {
                    entreComillas = !entreComillas;
                }
            } else if (c == ',' && !entreComillas) {
                campos.add(actual.toString());
                actual.setLength(0);
            } else {
                actual.append(c);
            }
        }

        campos.add(actual.toString());
        return campos;
    }

}