package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader menuPrincipalLoader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
        Parent menuPrincipalRoot = menuPrincipalLoader.load();
        MenuPrincipalController menuPrincipalController = menuPrincipalLoader.getController();

        Scene scene = new Scene(menuPrincipalRoot);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());

        // Définir la scène et le plein écran avant d'afficher la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("alt+F4"));
        primaryStage.show();

        menuPrincipalController.setScene(scene);
    }
}

