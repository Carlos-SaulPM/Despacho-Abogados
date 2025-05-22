package mx.unam.aragon.despachoabogados.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class appController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}