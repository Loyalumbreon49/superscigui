package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import supersci.gui.App;
public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}