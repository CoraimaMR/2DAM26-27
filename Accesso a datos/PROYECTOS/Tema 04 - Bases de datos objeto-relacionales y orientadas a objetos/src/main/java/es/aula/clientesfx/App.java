package es.aula.clientesfx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/** Arranque JavaFX. Ejecutar con Maven: clean javafx:run. */
public final class App extends Application {
    @Override public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("principal.fxml"));
        Scene scene = new Scene(loader.load(), 1160, 780);
        scene.getStylesheets().add(App.class.getResource("estilos.css").toExternalForm());
        PrincipalController controller = loader.getController();
        stage.setTitle("Clientes · JDBC y PostgreSQL objeto-relacional");
        stage.setScene(scene);
        stage.setMinWidth(960); stage.setMinHeight(640);
        stage.setOnHidden(event -> controller.cerrar());
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
