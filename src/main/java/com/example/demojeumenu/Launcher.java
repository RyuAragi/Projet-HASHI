package com.example.demojeumenu;

import javafx.application.Application;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Launcher {
    public static void main(String[] args) throws IOException {
        Application.launch(App.class, args);
//        Sauvegarde maSave = new Sauvegarde();
//
//        maSave.creer_arborescence();
//        maSave.creer_dossier_joueur("Nathan");
//
//        InputStream stream = GrilleJeu.class.getResourceAsStream("niveaux/facile/Facile-5.txt");
//        InputStreamReader reader = new InputStreamReader(stream);
//        GrilleJeu monJeu = new GrilleJeu(reader);
//        monJeu.creer_sauvegarde("niveaux/Facile/Facile-5.txt");
//        monJeu.charger_sauvegarde("Facile-5.ser");
    }
}
