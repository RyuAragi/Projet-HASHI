package com.example.demojeumenu.controler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Classe permettant de gérer les variables globales
 */
public class GlobalVariables {
    /**
     * Valeur par défaut de l'input utilisateur
     */
    private static final String DEFAULT_USER_INPUT = "Entrez votre nom";

    /**
     * Propriété de l'input utilisateur
     */
    private static final StringProperty userInput = new SimpleStringProperty(DEFAULT_USER_INPUT);
    /**
     * Propriété de l'input utilisateur
     */
    public static String getUserInput() {
        return userInput.get();
    }
    /**
     * Setter de l'input utilisateur
     */
    public static void setUserInput(String value) {
        userInput.set(value);
    }

    /**
     * Propriété de l'input utilisateur
     */
    public static StringProperty userInputProperty() {
        return userInput;
    }
    /**
     * Getter de l'input utilisateur par défaut
     */
    public static String getDefaultUserInput() {
        return DEFAULT_USER_INPUT;
    }

    //e
    /**
     * Variable globale pour savoir si le joueur, je suis dans une partie
     */
    public static SimpleBooleanProperty inGame = new SimpleBooleanProperty(false);
    static {
        // Add a change listener to inGame
        inGame.addListener((observable, oldValue, newValue) -> {
            System.out.println("inGame changed from " + oldValue + " to " + newValue);
        });
    }
    /**
     * Getter de la variable globale pour savoir si le joueur est dans une partie
     */
    public static boolean isInGame() {
        return inGame.get();
    }
    /**
     * Setter de la variable globale pour savoir si le joueur est dans une partie
     */
    public static void setInGame(boolean value) {
        inGame.set(value);
    }
    /**
     * Propriété de la variable globale pour savoir si le joueur est dans une partie
     */
    public static BooleanProperty inGameProperty() {
        return inGame;
    }

}
