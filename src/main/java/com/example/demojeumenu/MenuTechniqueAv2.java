
package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueAv2 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("MenuTechniqueAv3.fxml", scene);
    }
}
