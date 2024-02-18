package com.example.demojeumenu;

import javafx.scene.Scene;

public abstract class BaseController {
    protected Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}