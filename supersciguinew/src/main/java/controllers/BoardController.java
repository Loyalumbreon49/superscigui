package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.SuperSci;
import model.UserList;

public class BoardController {

    @FXML
    void buttonUp(ActionEvent event) {
        System.out.println("buttonUp");
    } 

    @FXML
    void buttonDown(ActionEvent event) {
        System.out.println("buttonDown");
    } 

    @FXML
    void buttonLeft(ActionEvent event) {
        System.out.println("buttonLeft");
    } 

    @FXML
    void buttonRight(ActionEvent event) {
        System.out.println("buttonRight");
    } 
    
}
