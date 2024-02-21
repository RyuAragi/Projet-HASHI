package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class HashiGame {
    @FXML
    private GridPane grid;

    public HashiGame(int size) {
        grid = new GridPane();

        // Ajouter les îles (cercles) à la grille
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Circle circle = new Circle(10); // 10 est le rayon du cercle
                grid.add(circle, i, j);
            }
        }

        // Ajouter les ponts (lignes) à la grille
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                Line line = new Line();
                line.setStartX(i);
                line.setStartY(j);
                line.setEndX(i + 1);
                line.setEndY(j + 1);
                grid.add(line, i, j);
            }
        }
    }

    public GridPane getGrid() {
        return grid;
    }
}