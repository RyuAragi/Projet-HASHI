package com.example.demojeumenu;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public abstract class BaseController {
    protected Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}