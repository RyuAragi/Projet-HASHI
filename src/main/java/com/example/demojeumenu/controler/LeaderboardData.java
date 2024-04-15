package com.example.demojeumenu.controler;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Classe permettant de gérer les données du leaderboard
 */
public class LeaderboardData {
    /**
     * Propriété du joueur
     */
    private final SimpleStringProperty player;
    /**
     * Propriété du score
     */
    private final SimpleIntegerProperty score;
    /**
     * Propriété du temps
     */
    private final SimpleStringProperty time;
    /**
     * Constructeur de la classe LeaderboardData
     * @param player Le joueur
     * @param score Le score
     * @param time Le temps
     */
    public LeaderboardData(String player, int score, String time) {
        this.player = new SimpleStringProperty(player);
        this.score = new SimpleIntegerProperty(score);
        this.time = new SimpleStringProperty(time);
    }
    /**
     * Getter du joueur
     */
    public String getPlayer() {
        return player.get();
    }
    /**
     * Getter du score
     */
    public int getScore() {
        return score.get();
    }
    /**
     * Getter du temps
     */
    public String getTime() {
        return time.get();
    }
}