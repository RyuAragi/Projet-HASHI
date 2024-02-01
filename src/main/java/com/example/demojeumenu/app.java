package com.example.demojeumenu;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class app extends Application {
    @Override
    public void start(Stage stage) {
        WindowManager windowManager = new WindowManager();
        windowManager.showView(stage, "hello-view");
    }


    public static void main(String[] args) {
        launch();
    }
}
