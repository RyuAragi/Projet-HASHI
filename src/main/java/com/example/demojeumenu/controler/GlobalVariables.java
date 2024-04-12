package com.example.demojeumenu.controler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GlobalVariables {

    private static final String DEFAULT_USER_INPUT = "Entrez votre nom";

    // Variables globales le nom de l'utilisateur
    private static final StringProperty userInput = new SimpleStringProperty(DEFAULT_USER_INPUT);

    public static String getUserInput() {
        return userInput.get();
    }

    public static void setUserInput(String value) {
        userInput.set(value);
    }

    public static StringProperty userInputProperty() {
        return userInput;
    }

    public static String getDefaultUserInput() {
        return DEFAULT_USER_INPUT;
    }

    //variable globale pour savoir si le joueur je suis dans une partie

    public static SimpleBooleanProperty inGame = new SimpleBooleanProperty(false);

    static {
        // Add a change listener to inGame
        inGame.addListener((observable, oldValue, newValue) -> {
            System.out.println("inGame changed from " + oldValue + " to " + newValue);
        });
    }

    public static boolean isInGame() {
        return inGame.get();
    }

    public static void setInGame(boolean value) {
        inGame.set(value);
    }

    public static BooleanProperty inGameProperty() {
        return inGame;
    }

}
