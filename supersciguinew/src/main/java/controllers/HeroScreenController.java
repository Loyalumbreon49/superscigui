package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import supersci.gui.App;

public class HeroScreenController {

    @FXML
    private Button Play_Button;

    @FXML
    private Label Uname_TextBox;

    @FXML
    void SwitchToBoard(ActionEvent event) throws IOException {
        App.setRoot("board");
    }

}

