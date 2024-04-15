package com.example.demojeumenu.controler;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;
/**
 * Controller for the leaderboard.
 */
@Controller
public class LeaderboardController {
    /**
     * The leaderboard VBox.
     */
    @FXML
    private VBox leaderboard;
    /**
     * Shows the leaderboard.
     */
    public void afficherLeaderboard() {
        leaderboard.setVisible(true);
    }
    /**
     * Hides the leaderboard.
     */
    public void cacherLeaderboard() {
        leaderboard.setVisible(false);
    }
}