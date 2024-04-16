package com.example.demojeumenu.controler;

import com.example.demojeumenu.GrilleControler;
import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.Menu.MenuTailleGrille;
import com.example.demojeumenu.SoundUtils;
import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.utils.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller du popup window.
 */
@Controller
public class PopupWindowControllerLB extends BaseController {

    /**
     * Tableau des scores.
     */
    @FXML
    private TableView<com.example.demojeumenu.controler.LeaderboardData> scoreTable;
    /**
     * Colonne des joueurs.
     */
    @FXML
    private TableColumn<com.example.demojeumenu.controler.LeaderboardData, String> playerColumn;
    /**
     * Colonne des scores.
     */
    @FXML
    private TableColumn<com.example.demojeumenu.controler.LeaderboardData, Integer> scoreColumn;
    /**
     * Colonne des temps.
     */
    @FXML
    private TableColumn<com.example.demojeumenu.controler.LeaderboardData, String> timeColumn;
    /**
     * Label du joueur.
     */
    public Label usernameLabel1;
    /**
     * Label du score.
     */
    public Label usernameLabel2;
    /**
     * Label du temps.
     */
    public Label usernameLabel3;
    /**
     * Chronomètre.
     */
    private static String chrono;
    /**
     * Score.
     */
    private static int score;
    /**
     * Bouton pour continuer.
     */
    @FXML
    private Button continueButton;
    /**
     * Bouton pour retourner.
     */
    @FXML
    private final StringProperty username = new SimpleStringProperty();
    /**
     * Fenêtre de l'application.
     */
    private static Stage stage;
    /**
     * Label du joueur.
     */
    @FXML
    private Label usernameLabel;
    /**
     * Bouton actionné pour retourner à l'écran précédent.
     */
    @FXML
    private Button back_button;

    // Méthode pour définir la fenêtre de l'application.
    /**
     * Définit la fenêtre de l'application.
     * @param st La fenêtre de l'application.
     */
    public static void setStage(Stage st){
        stage = st;
    }

    /**
     * Définit la scène.
     * @param stage La fenêtre de l'application.
     * @param scene La scène.
     */
    public void setScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    /**
     * Méthode pour revenir à l'écran d'accueil.
     */
    @FXML
    private void btnHome() {
        stage.close();
        FXMLUtils.loadFXML("/MenuPrincipal.fxml", scene);
    }
    // Méthode pour gérer le clic sur le bouton de retour.
    /**
     * Méthode pour gérer le clic sur le bouton de retour.
     */
    @FXML
    private void backButton() {
        // Code pour fermer la fenêtre du popup
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }
    /**
     * Classe pour les données du tableau des scores.
     */
    public static class LeaderboardData {
        private String player;
        private int score;
        private String time;

    }
    /**
     * Lit les données du tableau des scores.
     * @param level Le niveau.
     * @return Les données du tableau des scores.
     */
    public List<com.example.demojeumenu.controler.LeaderboardData> readLeaderboard(String level) {
        String content;
        List<com.example.demojeumenu.controler.LeaderboardData> leaderboardDataList = new ArrayList<>();
        try {
            content = new String(Files.readAllBytes(Paths.get("JacobHashi/Sauvegarde/Leaderboard.json")));
            JSONObject leaderboard = new JSONObject(content);
            if (leaderboard.has(level)) {
                JSONObject levelData = leaderboard.getJSONObject(level);
                for (String key : levelData.keySet()) {
                    JSONArray playerData = levelData.getJSONArray(key);
                    for (int i = 0; i < playerData.length(); i++) {
                        JSONObject data = playerData.getJSONObject(i);
                        String name = data.getString("name");
                        String time = data.getString("time");
                        int score = data.getInt("score");
                        leaderboardDataList.add(new com.example.demojeumenu.controler.LeaderboardData(name, score, time));
                    }
                }
            } else {
                System.out.println("No data for level: " + level);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderboardDataList;
    }
    /**
     * Définit le chronomètre.
     * @param time Le temps.
     */
    public static void setChrono(String time){
        chrono = time;
    }
    /**
     * Définit le score.
     * @param scr Le score.
     */
    public static void setScore(int scr){
        score = scr;
    }
    /**
     * Méthode appelée lors de l'initialisation du contrôleur.
     */
    @FXML
    public void initialize(){
        List<com.example.demojeumenu.controler.LeaderboardData> leaderboardDataList = readLeaderboard(MenuTailleGrille.level_info);

        ObservableList<com.example.demojeumenu.controler.LeaderboardData> data = FXCollections.observableArrayList(leaderboardDataList);

        playerColumn.setCellValueFactory(new PropertyValueFactory<>("player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        scoreTable.setItems(data);

        usernameLabel2.setText(String.valueOf(score));
        usernameLabel3.setText(chrono);
        usernameLabel.setText(GlobalVariables.getUserInput());
        SoundUtils.addClickSound(continueButton, this::backButton);
        back_button.setOnAction(event -> {
            FXMLUtils.loadFXML("/GrilleDisplay.fxml", scene, GrilleControler.loadedFile, GrilleControler.typePont,false);
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
        });
    }
}