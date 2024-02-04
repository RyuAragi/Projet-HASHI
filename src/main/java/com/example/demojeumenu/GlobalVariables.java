package com.example.demojeumenu;

public class GlobalVariables {

    // Variables globales le nom de l'utilisateur
    public static String userInput;

    // méthode pour récupérer le nom de l'utilisateur
    public static String getUser() {
        System.out.println("GlobalVariables.getUser() : " + userInput);
        return userInput;
    }

}