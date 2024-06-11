package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import supersci.gui.App;

public class HomeController {

    @FXML
    private Button createAccount_button;

    @FXML
    private Button login_button;

    @FXML
    void switchToCreateAccount(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

}
