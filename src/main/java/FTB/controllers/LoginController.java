
package FTB.controllers;

import FTB.exceptions.EmptyPassword;
import FTB.exceptions.InvalidPassword;
import FTB.exceptions.InvalidUsername;
import FTB.exceptions.UserEmpty;
import FTB.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import FTB.model.User;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginController {
    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    @FXML
    public void handleLoginButtonAction() throws InvalidPassword, EmptyPassword, UserEmpty, InvalidUsername {
        try {
            UserService.checkUser(usernameField.getText(), passwordField.getText());
            loginMessage.setText("e ok");

        } catch (InvalidUsername e) {
            loginMessage.setText(e.getMessage());

        } catch (InvalidPassword e) {
            loginMessage.setText(e.getMessage());

        } catch (EmptyPassword e) {
            loginMessage.setText(e.getMessage());

        } catch (UserEmpty e) {
            loginMessage.setText(e.getMessage());

        }
    }


}