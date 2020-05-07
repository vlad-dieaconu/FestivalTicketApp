
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    public TextField user_input;

    @FXML
    public void handleLoginButtonAction() throws InvalidPassword, EmptyPassword, UserEmpty, InvalidUsername {
        try {
            UserService.checkUser(user_input.getText(), passwordField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Login reusit !", ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();

        } catch (InvalidUsername e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();

        } catch (InvalidPassword e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        } catch (EmptyPassword e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        } catch (UserEmpty e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        }
    }


}