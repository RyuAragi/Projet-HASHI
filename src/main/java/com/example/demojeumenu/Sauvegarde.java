/**
 * @author GEORGET Rémy
 * @version 1.0
 */

package com.example.demojeumenu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Sauvegarde {

    private final String path = System.getProperty("user.dir") + "/JacobHashi/Sauvegarde/";

    private final ArrayList<String> liste_difficultes;
    /**
     * Constructeur de la classe
     */
    public Sauvegarde() {
        liste_difficultes = new ArrayList<String>();
        liste_difficultes.add("Facile");
        liste_difficultes.add("Moyen");
        liste_difficultes.add("Difficile");
    }

    /**
     * Getter de path
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * Methode permettant de créer l'arborescence de fichiers qu'on va éditer
     * → Les dossiers qui stockent la Sauvegarde des Niveaux
     * → Le dossier qui stocke le Leaderboard
     * Arborescence crée (Les dossiers des joueurs sont créé par la méthode creer_dossier_joueur())
     * |-> JacobHashi
     *          |-> Sauvegarde
     *              |-> Leaderboard.json
     *              |-> Facile
     *                  |-> Joueur1
     *                      |-> Facile-1.ser
     *                      |-> ...
     *                      |-> Facile-10.ser
     *              |-> Moyen
     *                  |-> Joueur1
     *                      |-> Moyen-1.ser
     *                      |-> ...
     *                      |-> Moyen-10.ser
     *              |-> Difficile
     *                  |-> Joueur1
     *                      |-> Difficile-1.ser
     *                      |-> ...
     *                      |-> Difficile-10.ser
     */
    public void creer_arborescence() throws IOException {
        File directory = new File(path);
        // Creation des repertoires JacobHashi/Sauvegarde
        if(!(directory.exists()) && directory.mkdirs()) {

            System.out.println("Repertoire JacobHashi/Sauvegarde bien crée !");

            for (String difficulte : liste_difficultes) {
                //System.out.println("Entrée dans la boucle : " + difficulte);
                String path_difficulte = path + difficulte;
                File directory_difficulte = new File(path_difficulte);
                if(directory_difficulte.mkdirs()) {
                    System.out.println("Repertoire " + difficulte + " bien crée ! ");
                }
            }
            this.creer_fichier_leaderboard();
        }
    }

    /**
     * Methode permettant de créer le dossier pour chaque joueur pour chaque difficulté
     * @param nom nom du joueur
     */
    public void creer_dossier_joueur(String nom) {
        for(String difficulte : liste_difficultes) {
            String path_difficulte = path + difficulte + "/" + nom;
            File directory_difficulte = new File(path_difficulte);
            if(directory_difficulte.mkdirs()) {
                System.out.println("Repertoire " + difficulte + "/" + nom + " bien crée ! ");
            }
        }
    }

    /**
     * Methode permettant de créer le fichier de leaderboard
     */
    public void creer_fichier_leaderboard() throws IOException {

        File fichier_json = new File(path + "Leaderboard.json");
        if (fichier_json.createNewFile()) {
            System.out.println("Fichier Leaderboard.json bien crée !");

            InputStream input = Score.class.getResourceAsStream("/Sauvegarde/Leaderboard.json");
            String json = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fichier_json, node);
        }
    }
}

