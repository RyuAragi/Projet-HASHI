package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
        Parent root = loader.load();
        MenuPrincipalController controller = loader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());

        javafx.application.Platform.runLater(() -> {
            String os = System.getProperty("os.name").toLowerCase();
            if (!os.contains("mac")) {
                Image image = new Image(Objects.requireNonNull(getClass().getResource("images/normal.png")).toExternalForm());
                Cursor customCursor = new ImageCursor(image);
                scene.setCursor(customCursor);
            }
        });

        controller.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("alt+F4"));
        FXMLUtils.applySceneStyles(scene);
        primaryStage.show();

        // Ajoutez manuellement le fichier FXML à l'historique
        FXMLUtils.addHistory("MenuPrincipal.fxml");
    }
}
