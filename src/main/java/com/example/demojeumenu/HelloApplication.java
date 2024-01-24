package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        URL boutonUrl = getClass().getResource("bouton.png");
        URL fondUrl = getClass().getResource("fond.png");

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


        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}