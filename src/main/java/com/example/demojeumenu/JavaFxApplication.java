package com.example.demojeumenu;

import com.example.demojeumenu.Menu.MenuPrincipalController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(Launcher.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuPrincipal.fxml"));
        Parent root = loader.load();
        MenuPrincipalController controller = loader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());

        javafx.application.Platform.runLater(() -> {
            String os = System.getProperty("os.name").toLowerCase();
            if (!os.contains("mac")) {
                Image image = new Image(Objects.requireNonNull(getClass().getResource("/images/normal.png")).toExternalForm());
                Cursor customCursor = new ImageCursor(image);
                scene.setCursor(customCursor);
            }
        });

        controller.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("alt+F4"));
        FXMLUtils.applySceneStyles(scene);

        // Ajoutez manuellement le fichier FXML à l'historique
        FXMLUtils.addHistory("/MenuPrincipal.fxml");

        primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                javafx.application.Platform.runLater(() -> primaryStage.setFullScreen(true));
            }
        });

        // Définir une taille par défaut pour la fenêtre en fonction de la taille de l'écran
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        // Maximiser la fenêtre au démarrage de l'application
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}