package com.example.demojeumenu;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Classe de lancement de l'application
 */
@SpringBootApplication
public class Launcher {
    /**
     * Méthode de lancement de l'application
     * @param args  arguments de la ligne de commande de l'application
     */
    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }

}
