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

public class CreateAccountController {

    @FXML
    private Button createAccountButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_phoneNumber;

    @FXML
    private TextField txt_username;

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String phoneNumber = txt_phoneNumber.getText();
        String email = txt_email.getText();
        
        UserList users = UserList.getInstance();
        SuperSci superSci = new SuperSci(users);
        boolean ifCreateAccount = superSci.createAccount(username, password, phoneNumber, email, users);

        if(!ifCreateAccount)
        {
            System.out.println("Invalid Entry");
            return;
        }
        System.out.println("Account Creation Complete");
        // App.setRoot("login");
    }

    @FXML
    void login(ActionEvent event) throws  IOException {
        App.setRoot("login");
    }
}
