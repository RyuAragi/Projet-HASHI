package com.example.demojeumenu;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Screen;

public abstract class BaseController {
    protected Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}