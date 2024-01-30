package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;
//import javafx.stage.Screen;
//import javafx.geometry.Rectangle2D;
//import javafx.stage.StageStyle;

import java.io.IOException;
//import java.net.URL;
import java.util.Objects;

public class app extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        stage.setScene(scene);

        WindowManager windowManager = new WindowManager(stage);
        windowManager.configureWindow();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
