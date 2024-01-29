package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);
        URL boutonUrl = getClass().getResource("images/bouton.png");
        URL fondUrl = getClass().getResource("images/fond.png");

        if (boutonUrl == null || fondUrl == null) {
            System.out.println("Images not found");
        } else {
            Image boutonImage = new Image(boutonUrl.toExternalForm());
            Image fondImage = new Image(fondUrl.toExternalForm());

            if (boutonImage.isError() || fondImage.isError()) {
                System.out.println("Error loading images");
            } else {
                System.out.println("Images loaded successfully");
            }
        }

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        stage.setTitle("Jacobhashi!");
        stage.setWidth(screenWidth-10);
        stage.setHeight(screenHeight-50);
        stage.setScene(scene);

        // Personnaliser la fenêtre pour qu'elle occupe tout l'écran
        stage.setFullScreen(true);

        // Désactiver les astuces en plein écran
        stage.setFullScreenExitHint("");

        // activer les boutons de la fenêtre (réduire, agrandir, fermer)
        stage.initStyle(StageStyle.UNIFIED);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
