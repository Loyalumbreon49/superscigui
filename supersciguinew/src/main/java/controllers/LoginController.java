package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.SuperSci;
import model.UserList;
import supersci.gui.App;

public class LoginController {

    @FXML
    private Button createAccountButton;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private Button primaryButton;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        App.setRoot("board");
    }

    @FXML
    void login(ActionEvent event) throws IOException{
        String username = txt_username.getText();
        String password = txt_password.getText();
        System.out.println("Username is"+ username);

        UserList users = UserList.getInstance();
        SuperSci superSci = new SuperSci(users);
        boolean ifLogin = superSci.login(username, password, users); //ALWAYS TYPE IN PASSWORD
        
        if(!ifLogin)
        {
            System.out.println("Invalid Entry");
            return;
        }
        System.out.println("Login Complete");
        App.setRoot("board");
    }

}
