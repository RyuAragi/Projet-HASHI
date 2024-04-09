package com.example.demojeumenu.controler;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

@Controller
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