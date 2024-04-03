package com.example.demojeumenu;

import javafx.application.Application;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        //Application.launch(App.class, args);
        Sauvegarde maSave = new Sauvegarde();

        maSave.creer_arborescence();
        maSave.creer_dossier_joueur("Nathan");
    }
}
