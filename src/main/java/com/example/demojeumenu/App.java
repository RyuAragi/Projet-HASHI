package com.example.demojeumenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
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

        // Get the screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenHeight = screenBounds.getHeight();

        System.out.println("screenHeight : "+screenHeight);

        // Get the .titre node
        Node titreNode = scene.lookup(".titre");

        // Calculate -fx-pref-height based on screenHeight
        double prefHeight;
        if (screenHeight <= 805.0) {
            prefHeight = 145;
        } else if (screenHeight <= 1032.0) {
            prefHeight = 145 + ((screenHeight - 805.0) * (170 - 145)) / (1032.0 - 805.0);
        } else if (screenHeight <= 1097.0) {
            prefHeight = 170 + ((screenHeight - 1032.0) * (190 - 170)) / (1097.0 - 1032.0);
        } else if (screenHeight <= 1392.0) {
            prefHeight = 190 + ((screenHeight - 1097.0) * (215 - 190)) / (1392.0 - 1097.0);
        } else if (screenHeight <= 1497.0) {
            prefHeight = 215 + ((screenHeight - 1392.0) * (240 - 215)) / (1497.0 - 1392.0);
        } else {
            prefHeight = 240;
        }

        // Apply the calculated style
        titreNode.setStyle("-fx-pref-height: " + prefHeight + "; -fx-text-fill: white; -fx-pref-width: 600;");



        // Définir la scène et le plein écran avant d'afficher la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("alt+F4"));
        primaryStage.show();

        menuPrincipalController.setScene(scene);
    }
}

