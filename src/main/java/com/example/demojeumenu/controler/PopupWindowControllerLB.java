package com.example.demojeumenu.controler;

import com.example.demojeumenu.GrilleControler;
import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.SoundUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class PopupWindowControllerLB extends BaseController {
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

    public static void setStage(Stage st){
        stage = st;
    }

    public void setScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }


    @FXML
    private void btnHome() {
        stage.close();
        FXMLUtils.loadFXML("/MenuPrincipal.fxml", scene);
    }

    @FXML
    private void backButton() {
        // Code pour fermer la fenêtre du popup
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
    }



    @FXML
    public void initialize(){
        usernameLabel.setText(GlobalVariables.getUserInput());
        SoundUtils.addClickSound(continueButton, this::backButton);
        usernameLabel3.setText(GrilleControler.chronoTime);
    }
}