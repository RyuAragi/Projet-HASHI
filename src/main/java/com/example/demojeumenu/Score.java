/**
 * @author GEORGET Rémy
 * @version 1.0
 */

package com.example.demojeumenu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Score {

    private final HashMap<String, HashMap<String, Integer>> niveaux;

    private final HashMap<String, Integer> joueurs;
    public Score() throws IOException {
        this.niveaux = new HashMap<String, HashMap<String, Integer>>();
        this.joueurs = new HashMap<String, Integer>();
        this.remplir_hashmap();
    }

    /**
     * Getter du HashMap des niveaux
     * @return HashMap&lt;String, HashMap&lt;String, Integer&gt;&gt;
     */
    public HashMap<String, HashMap<String, Integer>> getNiveaux() {
        return niveaux;
    }

    public HashMap<String, Integer> getJoueurs(String niveau) {
        return getNiveaux().get(niveau);
    }

    /**
     * Methode permettant d'avoir le json sous forme de node
     * @return JsonNode
     */
    private JsonNode getNode() throws IOException {
        InputStream input = Score.class.getResourceAsStream("Sauvegarde/Leaderboard.json");
        String json = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json);
    }

    /**
     * Methode permettant de remplir le hashmap.
     * Elle charge le fichier json et convertit toutes les valeurs dans le hashmap
     */
    private void remplir_hashmap() throws IOException {

        // Recuperation du json
        JsonNode node = this.getNode();

        Iterator<JsonNode> jsonNodeIterateur = node.iterator();
        Iterator<String> nomNiveauxIterateur = node.fieldNames();

        JsonNode node_actuel =  null;
        String nom_niveau_actuel = null;

        // Remplissage du Hashmap
        while(jsonNodeIterateur.hasNext() && nomNiveauxIterateur.hasNext()) {
            node_actuel = jsonNodeIterateur.next();
            nom_niveau_actuel = nomNiveauxIterateur.next();
            
            ArrayList<String> liste_nom = new ArrayList<String>();
            Iterator<String> iterateur_nom = node_actuel.fieldNames();
            iterateur_nom.forEachRemaining(liste_nom::add);
            
            for(String nom_actuel : liste_nom) {
                this.joueurs.put(nom_actuel, node_actuel.path(nom_actuel).asInt());
            }
            
            this.niveaux.put(nom_niveau_actuel, joueurs);
        }
    }

    /**
     * Methode permttant d'ajouter un joueur au leaderboard général
     * La methode ajoute pour chaque niveau le nom du joueur en clé ainsi qu'un score de -1.
     * Elle est utilisable dans le cas ou le joueur n'est pas renseigné, c'est-à-dire que c'est un nouveau joueur.
     * @param nom_joueur Nom du joueur à ajouter
     */
    public void ajouter_joueur(String nom_joueur) throws IOException{
        JsonNode node = getNode();
        ObjectMapper mapper = new ObjectMapper();
        File f = new File(Score.class.getResource("Sauvegarde/Leaderboard.json").getPath());

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        for(JsonNode n : node) {
            ((ObjectNode) n).put(nom_joueur, -1);
        }
        System.out.println(node);

        mapper.writeValue(f, node);
    }

    /**
     * Methode permettant de changer le score d'un joueur du niveau
     * @param score Score du joueur à actualiser
     */
    public void changer_score(int score, String niveau, String nom_joueur) throws IOException {
        JsonNode node = getNode();
        JsonNode node_niveau = node.path(niveau);
        ObjectMapper mapper = new ObjectMapper();

        ((ObjectNode) node_niveau).put(nom_joueur, score);
        ((ObjectNode) node).set(niveau, node_niveau);

        File f = new File(Score.class.getResource("Sauvegarde/Test.json").getPath());
        mapper.writerWithDefaultPrettyPrinter().writeValue(f, node);
    }

    /**
     * Methode permettant de verifier si un joueur existe ou non dans le json
     * @param nom nom du joueur
     * @return boolean
     */
    public boolean joueur_existe(String nom) throws IOException {
        return getJoueurs("Facile-1").containsKey(nom);
    }

/*    public static void main(String args[]) throws IOException {
        Score monScore = new Score();
        monScore.changer_score(10000, "Facile-1.txt", "Michel");
    }*/
}

