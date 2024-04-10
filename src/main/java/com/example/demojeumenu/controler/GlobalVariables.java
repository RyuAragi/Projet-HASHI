package com.example.demojeumenu.controler;

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
}
