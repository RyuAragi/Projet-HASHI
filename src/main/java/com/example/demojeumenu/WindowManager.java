package com.example.demojeumenu;

//import javafx.stage.Screen;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.control.TextFormatter.Change;

public class WindowManager {
    private final Stage stage;

    public WindowManager(Stage stage) {
        this.stage = stage;
    }

    public void configureWindow() {
        // Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        // double screenWidth = screenBounds.getWidth();
        // double screenHeight = screenBounds.getHeight();

        stage.setTitle("Jacobhashi!");
        // stage.setWidth(screenWidth-10);
        // stage.setHeight(screenHeight-50);

        // Personnaliser la fenêtre pour qu'elle occupe tout l'écran
        stage.setFullScreen(true);

        // Désactiver les astuces en plein écran
        stage.setFullScreenExitHint("");

        // activer les boutons de la fenêtre (réduire, agrandir, fermer)
        // stage.initStyle(StageStyle.UNIFIED);
    }
}