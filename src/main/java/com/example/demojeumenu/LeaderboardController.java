package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class LeaderboardController {

    @FXML
    private VBox leaderboard;

    public void afficherLeaderboard() {
        leaderboard.setVisible(true);
    }

    public void cacherLeaderboard() {
        leaderboard.setVisible(false);
    }
}