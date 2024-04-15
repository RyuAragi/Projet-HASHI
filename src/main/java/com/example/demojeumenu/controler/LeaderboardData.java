package com.example.demojeumenu.controler;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LeaderboardData {
    private final SimpleStringProperty player;
    private final SimpleIntegerProperty score;
    private final SimpleStringProperty time;

    public LeaderboardData(String player, int score, String time) {
        this.player = new SimpleStringProperty(player);
        this.score = new SimpleIntegerProperty(score);
        this.time = new SimpleStringProperty(time);
    }

    public String getPlayer() {
        return player.get();
    }

    public int getScore() {
        return score.get();
    }

    public String getTime() {
        return time.get();
    }
}