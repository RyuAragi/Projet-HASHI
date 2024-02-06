package com.example.demojeumenu;

public class GlobalVariables {

    // Variables globales le nom de l'utilisateur
    public static String userInput;


    public static int sound;

    // méthode pour récupérer le nom de l'utilisateur
    public static String getUser() {
        System.out.println("GlobalVariables.getUser() : " + userInput);
        return userInput;
    }

    public static int getSound(){
        return sound;
    }

    public static void incrementSound(){
        /* A compléter */
    }

    public static void decrementSound(){
        /* A compléter */
    }
}