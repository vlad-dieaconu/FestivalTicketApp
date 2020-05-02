package FTB.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import FTB.exceptions.UsernameAlreadyExistsException;
import FTB.services.UserService;


public class RegisterController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;


    @FXML
    public void handleRegisterAction() {
        try {
           UserService.addUser(usernameField.getText(), passwordField.getText());
          registrationMessage.setText("Account created successfully!");
       } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
   }
}
