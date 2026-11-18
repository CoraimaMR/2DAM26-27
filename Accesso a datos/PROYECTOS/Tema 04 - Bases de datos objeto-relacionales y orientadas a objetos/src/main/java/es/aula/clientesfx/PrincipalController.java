package es.aula.clientesfx;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Consumer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
/** Interfaz completa. El alumnado concentra los TODO en ClienteDao. */
public final class PrincipalController {
    @FXML private BorderPane zonaTrabajo;
    @FXML private Label lblEstado, lblTotal;
    @FXML private TableView<ClienteBasico> tablaRepaso;
    @FXML private TableColumn<ClienteBasico, String> rId, rNombre, rEmail, rCiudad;
    @FXML private TextField txtRNombre, txtREmail, txtRCiudad, txtRFiltro;
    @FXML private TableView<Cliente> tablaObjetos;
    @FXML private TableColumn<Cliente, String> oId, oNombre, oEmail, oDireccion, oTelefonos, oJson;
    @FXML private TextField txtONombre, txtOEmail, txtOCalle, txtOCiudad, txtOCp, txtOTelefonos, txtOFiltro;
    @FXML private TextArea txtOJson;
    private final ClienteBasicoDao repaso = new ClienteBasicoDao();
    private final ClienteRepository objetos = new ClienteDao();
    // Un solo hilo evita operaciones simultáneas. Es daemon para permitir cerrar la aplicación.
    private final ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread hilo = new Thread(r, "jdbc-aula"); hilo.setDaemon(true); return hilo;
    });
    private boolean cerrado;

    @FXML private void initialize() {
        // Los records tienen id(), nombre()... y no getId(). Usamos lambdas, sin reflexión.
        rId.setCellValueFactory(c -> valor(c.getValue().id()));
        rNombre.setCellValueFactory(c -> valor(c.getValue().nombre()));
        rEmail.setCellValueFactory(c -> valor(c.getValue().email()));
        rCiudad.setCellValueFactory(c -> valor(c.getValue().ciudad()));
        oId.setCellValueFactory(c -> valor(c.getValue().id()));
        oNombre.setCellValueFactory(c -> valor(c.getValue().nombre()));
        oEmail.setCellValueFactory(c -> valor(c.getValue().email()));
        oDireccion.setCellValueFactory(c -> valor(c.getValue().direccion().calle() + ", " +
            c.getValue().direccion().ciudad() + " (" + c.getValue().direccion().codigoPostal() + ")"));
        oTelefonos.setCellValueFactory(c -> valor(String.join("; ", c.getValue().telefonos())));
        oJson.setCellValueFactory(c -> valor(c.getValue().preferenciasJson()));
        tablaRepaso.setPlaceholder(new Label("Pulsa Cargar todos para consultar el repaso."));
        tablaObjetos.setPlaceholder(new Label("Completa listar() y pulsa Cargar todos."));
        tablaRepaso.getSelectionModel().selectedItemProperty().addListener((obs,antes,c) -> {
            if (c != null) { txtRNombre.setText(c.nombre()); txtREmail.setText(c.email()); txtRCiudad.setText(c.ciudad()); }
        });
        tablaObjetos.getSelectionModel().selectedItemProperty().addListener((obs,antes,c) -> {
            if (c != null) {
                txtONombre.setText(c.nombre()); txtOEmail.setText(c.email());
                txtOCalle.setText(c.direccion().calle()); txtOCiudad.setText(c.direccion().ciudad());
                txtOCp.setText(c.direccion().codigoPostal());
                txtOTelefonos.setText(String.join(";",c.telefonos())); txtOJson.setText(c.preferenciasJson());
            }
        });
        // La ventana se abre aunque la base no esté aún montada.
        lblEstado.setText("Interfaz preparada. Monta la base y pulsa Comprobar conexión.");
    }
    private static ReadOnlyStringWrapper valor(Object s) { return new ReadOnlyStringWrapper(String.valueOf(s)); }

    /** Capturamos los datos de controles ANTES de entrar al hilo JDBC. */
    private <T> void ejecutar(String mensaje, Callable<T> operacion, Consumer<T> alTerminar) {
        if (cerrado) return;
        zonaTrabajo.setDisable(true); lblEstado.setText(mensaje);
        Task<T> tarea = new Task<>() {
            @Override protected T call() throws Exception { return operacion.call(); }
        };
        tarea.setOnSucceeded(e -> {
            if (cerrado) return;
            zonaTrabajo.setDisable(false);
            lblEstado.setText("Operación completada.");
            alTerminar.accept(tarea.getValue());
        });
        tarea.setOnFailed(e -> {
            if (cerrado) return;
            zonaTrabajo.setDisable(false); error(tarea.getException());
        });
        executor.execute(tarea);
    }
    @FXML private void comprobarConexion() {
        ejecutar("Comprobando conexión…", () -> {
            try (Connection c = Conexion.abrir()) {
                return c.getMetaData().getDatabaseProductName() + " · " + c.getCatalog();
            }
        }, info -> lblEstado.setText("Conexión correcta: " + info));
    }
    @FXML private void cargarRepaso() { txtRFiltro.clear(); listarRepaso(null); }
    @FXML private void filtrarRepaso() { listarRepaso(txtRFiltro.getText().trim()); }
    private void listarRepaso(String ciudad) {
        ejecutar("Cargando repaso…", () -> repaso.listar(ciudad), lista -> {
            tablaRepaso.getItems().setAll(lista); limpiarRepaso();
            lblEstado.setText("Repaso: " + lista.size() + " clientes visibles.");
        });
    }
    @FXML private void insertarRepaso() {
        try {
            ClienteBasico c = new ClienteBasico(0, Validacion.obligatorio(txtRNombre.getText(), "Nombre"),
                Validacion.email(txtREmail.getText()), Validacion.obligatorio(txtRCiudad.getText(), "Ciudad"));
            ejecutar("Insertando cliente de repaso…", () -> {
                long id = repaso.insertar(c);
                return new Cambio<>("Cliente creado con id=" + id, repaso.listar(null));
            }, this::mostrarCambioRepaso);
        } catch (IllegalArgumentException e) { aviso(e.getMessage()); }
    }
    @FXML private void actualizarRepaso() {
        ClienteBasico elegido = tablaRepaso.getSelectionModel().getSelectedItem();
        if (elegido == null) { aviso("Selecciona un cliente de repaso."); return; }
        try {
            ClienteBasico c = new ClienteBasico(elegido.id(), Validacion.obligatorio(txtRNombre.getText(), "Nombre"),
                Validacion.email(txtREmail.getText()), Validacion.obligatorio(txtRCiudad.getText(), "Ciudad"));
            ejecutar("Actualizando cliente de repaso…", () -> {
                boolean ok = repaso.actualizar(c);
                return new Cambio<>(ok ? "Cliente actualizado." : "El cliente ya no existe.", repaso.listar(null));
            }, this::mostrarCambioRepaso);
        } catch (IllegalArgumentException e) { aviso(e.getMessage()); }
    }
    @FXML private void eliminarRepaso() {
        ClienteBasico c = tablaRepaso.getSelectionModel().getSelectedItem();
        if (c == null) { aviso("Selecciona un cliente de repaso."); return; }
        if (!confirmar("Eliminar a " + c.nombre() + " del repaso")) return;
        ejecutar("Eliminando cliente de repaso…", () -> {
            boolean ok = repaso.eliminar(c.id());
            return new Cambio<>(ok ? "Cliente eliminado." : "El cliente ya no existe.", repaso.listar(null));
        }, this::mostrarCambioRepaso);
    }
    private void mostrarCambioRepaso(Cambio<ClienteBasico> cambio) {
        tablaRepaso.getItems().setAll(cambio.filas()); txtRFiltro.clear(); limpiarRepaso();
        lblEstado.setText(cambio.mensaje());
    }
    @FXML private void limpiarRepaso() {
        tablaRepaso.getSelectionModel().clearSelection(); txtRNombre.clear(); txtREmail.clear(); txtRCiudad.clear();
    }
    @FXML private void cargarObjetos() { txtOFiltro.clear(); listarObjetos(null); }
    @FXML private void filtrarObjetos() {
        String s = txtOFiltro.getText().trim(); listarObjetos(s.isBlank() ? null : s);
    }
    private void listarObjetos(String ciudad) {
        ejecutar("Cargando clientes objeto-relacionales…", () -> objetos.listar(ciudad), lista -> {
            tablaObjetos.getItems().setAll(lista); limpiarObjetos();
            lblEstado.setText("Objeto-relacional: " + lista.size() + " clientes visibles.");
        });
    }
    @FXML private void insertarObjetos() {
        try {
            Cliente cliente = new Cliente(0, Validacion.obligatorio(txtONombre.getText(), "Nombre"),
                Validacion.email(txtOEmail.getText()),
                new Direccion(Validacion.obligatorio(txtOCalle.getText(), "Calle"),
                    Validacion.obligatorio(txtOCiudad.getText(), "Ciudad"), Validacion.cp(txtOCp.getText())),
                Validacion.telefonos(txtOTelefonos.getText()),
                Validacion.obligatorio(txtOJson.getText(), "Preferencias JSON"));
            ejecutar("Insertando cliente objeto-relacional…", () -> {
                long id = objetos.insertar(cliente);
                return new Cambio<>("Cliente creado con id=" + id, objetos.listar(null));
            }, this::mostrarCambioObjetos);
        } catch (IllegalArgumentException e) { aviso(e.getMessage()); }
    }
    @FXML private void actualizarContacto() {
        Cliente c = seleccionado(); if (c == null) return;
        try {
            String email = Validacion.email(txtOEmail.getText());
            List<String> telefonos = Validacion.telefonos(txtOTelefonos.getText());
            ejecutar("Actualizando contacto…", () -> {
                boolean ok = objetos.actualizarContacto(c.id(), email, telefonos);
                return new Cambio<>(ok ? "Email y teléfonos actualizados." : "El cliente ya no existe.", objetos.listar(null));
            }, this::mostrarCambioObjetos);
        } catch (IllegalArgumentException e) { aviso(e.getMessage()); }
    }
    @FXML private void cambiarCiudad() {
        Cliente c = seleccionado(); if (c == null) return;
        try {
            String ciudad = Validacion.obligatorio(txtOCiudad.getText(), "Ciudad nueva");
            ejecutar("Ejecutando procedimiento…", () -> {
                objetos.cambiarCiudad(c.id(), ciudad);
                return new Cambio<>("Ciudad cambiada mediante el procedimiento.", objetos.listar(null));
            }, this::mostrarCambioObjetos);
        } catch (IllegalArgumentException e) { aviso(e.getMessage()); }
    }
    @FXML private void cambiarJson() {
        Cliente c = seleccionado(); if (c == null) return;
        try {
            String json = Validacion.obligatorio(txtOJson.getText(), "Preferencias JSON");
            ejecutar("Actualizando JSONB…", () -> {
                boolean ok = objetos.cambiarPreferencias(c.id(), json);
                return new Cambio<>(ok ? "Preferencias sustituidas." : "El cliente ya no existe.", objetos.listar(null));
            }, this::mostrarCambioObjetos);
        } catch (IllegalArgumentException e) { aviso(e.getMessage()); }
    }
    @FXML private void eliminarObjetos() {
        Cliente c = seleccionado(); if (c == null) return;
        if (!confirmar("Eliminar a " + c.nombre() + " de objeto-relacional")) return;
        ejecutar("Eliminando cliente…", () -> {
            boolean ok = objetos.eliminar(c.id());
            return new Cambio<>(ok ? "Cliente eliminado." : "El cliente ya no existe.", objetos.listar(null));
        }, this::mostrarCambioObjetos);
    }
    @FXML private void totalObjetos() {
        ejecutar("Consultando función total_clientes…", objetos::total,
            total -> { lblTotal.setText("Total en BD: " + total); lblEstado.setText("Función ejecutada correctamente."); });
    }
    private void mostrarCambioObjetos(Cambio<Cliente> cambio) {
        tablaObjetos.getItems().setAll(cambio.filas()); txtOFiltro.clear(); limpiarObjetos();
        // Evitamos mostrar un total calculado antes del alta o del borrado.
        lblTotal.setText("Total en BD: pulsa Consultar total"); lblEstado.setText(cambio.mensaje());
    }
    @FXML private void limpiarObjetos() {
        tablaObjetos.getSelectionModel().clearSelection(); txtONombre.clear(); txtOEmail.clear();
        txtOCalle.clear(); txtOCiudad.clear(); txtOCp.clear(); txtOTelefonos.clear();
        txtOJson.setText("{\"canal\":\"email\"}");
    }
    private Cliente seleccionado() {
        Cliente c = tablaObjetos.getSelectionModel().getSelectedItem();
        if (c == null) aviso("Selecciona un cliente de la tabla objeto-relacional."); return c;
    }
    private boolean confirmar(String texto) {
        Alert a = alerta(Alert.AlertType.CONFIRMATION, "Confirmar eliminación", texto + "?");
        Optional<ButtonType> r = a.showAndWait(); return r.isPresent() && r.get() == ButtonType.OK;
    }
    private void aviso(String texto) { alerta(Alert.AlertType.INFORMATION, "Revisa los datos", texto).showAndWait(); }
    private void error(Throwable error) {
        String detalle = error.getMessage() == null ? error.getClass().getSimpleName() : error.getMessage();
        if (error instanceof SQLException e) {
            detalle = switch (e.getSQLState() == null ? "" : e.getSQLState()) {
                case "0A000" -> e.getMessage();
                case "23505" -> "Ya existe un cliente con ese email.";
                case "23514" -> "Se incumple una restricción. Revisa dirección, CP y canal del JSON.";
                case "22P02" -> "El JSON o algún dato enviado no tiene un formato válido.";
                case "P0002" -> "El cliente seleccionado ya no existe.";
                case "42P01", "3F000" -> "Falta el esquema o la tabla. Ejecuta los scripts de montaje indicados en la guía.";
                case "42883" -> "No existe la función o el procedimiento. Completa el script de rutinas.";
                default -> "Error SQL [" + e.getSQLState() + "]: " + detalle;
            };
        }
        lblEstado.setText("No se pudo completar la operación.");
        alerta(Alert.AlertType.ERROR, "Operación pendiente o error", detalle).showAndWait();
    }
    private Alert alerta(Alert.AlertType tipo, String titulo, String texto) {
        Alert a = new Alert(tipo); a.setTitle(titulo); a.setHeaderText(null); a.setContentText(texto);
        if (zonaTrabajo.getScene() != null) a.initOwner(zonaTrabajo.getScene().getWindow());
        a.setResizable(true); return a;
    }
    public void cerrar() { cerrado = true; executor.shutdownNow(); }
    private record Cambio<T>(String mensaje, List<T> filas) { }
}
