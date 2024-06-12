package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import supersci.gui.App;

public class WinController {

    @FXML
    private Button restart_button;

    @FXML
    void switchToPlayScreen(ActionEvent event) throws IOException {
        App.setRoot("hero_screen");
    }

}
