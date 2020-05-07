package FTB.controllers;

import FTB.exceptions.EmptyPassword;
import FTB.exceptions.UserEmpty;
import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (EmptyPassword emptyPassword) {
            emptyPassword.printStackTrace();
        } catch (UserEmpty userEmpty) {
            userEmpty.printStackTrace();
        }
    }
}
