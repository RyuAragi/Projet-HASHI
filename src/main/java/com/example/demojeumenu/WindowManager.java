package com.example.demojeumenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WindowManager {
    private final Map<String, Parent> views = new HashMap<>();

    public WindowManager() {
        // Pré-charger toutes les vues
        views.put("hello-view", loadView("hello-view.fxml"));
        views.put("game-view", loadView("game-view.fxml"));
        // Ajoutez d'autres vues ici
    }

    private Parent loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demojeumenu/" + fxmlFile));
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("echec du chargement du fichier " + fxmlFile, e);
        }
    }

    public void showView(Stage stage, String viewName) {
        Parent view = views.get(viewName);
        if (view == null) {
            throw new IllegalArgumentException("No view with name: " + viewName);
        }
        Scene scene = new Scene(view);
        stage.setScene(scene);

        //desactive les astuces de plein écran
        stage.setFullScreenExitHint("");

        //stage.setFullScreen(true); //active le mode plein écran
        stage.show();
    }
}