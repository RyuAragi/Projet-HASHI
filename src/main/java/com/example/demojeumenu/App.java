package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //test

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader menuPrincipalLoader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
        Parent menuPrincipalRoot = menuPrincipalLoader.load();
        MenuPrincipalController menuPrincipalController = menuPrincipalLoader.getController();

        Scene scene = new Scene(menuPrincipalRoot, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);

        menuPrincipalController.setScene(scene);

    }
}
