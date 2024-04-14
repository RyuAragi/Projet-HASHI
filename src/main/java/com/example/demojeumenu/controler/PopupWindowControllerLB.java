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
//just pour vérifier
@Controller
public class PopupWindowControllerLB extends BaseController {

    @FXML
    private TableView<com.example.demojeumenu.controler.LeaderboardData> scoreTable;

    @FXML
    private TableColumn<com.example.demojeumenu.controler.LeaderboardData, String> playerColumn;

    @FXML
    private TableColumn<com.example.demojeumenu.controler.LeaderboardData, Integer> scoreColumn;

    @FXML
    private TableColumn<com.example.demojeumenu.controler.LeaderboardData, String> timeColumn;
    public Label usernameLabel1;
    public Label usernameLabel2;
    public Label usernameLabel3;

    @FXML
    private Button continueButton;

    @FXML
    //private Button newGameButton;
    private final StringProperty username = new SimpleStringProperty();

    private static Stage stage;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button back_button;

    // Méthode pour définir la fenêtre de l'application.

    public static void setStage(Stage st){
        stage = st;
    }


    // Méthode pour définir la scène.

    public void setScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    // Méthode pour revenir à l'écran d'accueil.

    @FXML
    private void btnHome() {
        stage.close();
        FXMLUtils.loadFXML("/MenuPrincipal.fxml", scene);
    }
    // Méthode pour gérer le clic sur le bouton de retour.

    @FXML
    private void backButton() {
        // Code pour fermer la fenêtre du popup
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }

    public static class LeaderboardData {
        private String player;
        private int score;
        private String time;

    }

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



    @FXML
    public void initialize(){
        List<com.example.demojeumenu.controler.LeaderboardData> leaderboardDataList = readLeaderboard(MenuTailleGrille.level_info);

        ObservableList<com.example.demojeumenu.controler.LeaderboardData> data = FXCollections.observableArrayList(leaderboardDataList);

        playerColumn.setCellValueFactory(new PropertyValueFactory<>("player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        scoreTable.setItems(data);

        usernameLabel.setText(GlobalVariables.getUserInput());
        SoundUtils.addClickSound(continueButton, this::backButton);
        usernameLabel3.setText(GrilleJeu.chronoTime);
        back_button.setOnAction(event -> {
            FXMLUtils.loadFXML("/GrilleDisplay.fxml", scene, GrilleControler.loadedFile, false);
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
        });
    }
}