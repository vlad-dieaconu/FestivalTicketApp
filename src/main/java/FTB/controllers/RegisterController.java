package FTB.controllers;

import FTB.exceptions.EmptyPassword;
import FTB.exceptions.UserEmpty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import FTB.exceptions.UsernameAlreadyExistsException;
import FTB.services.UserService;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(),email.getText(),age.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (EmptyPassword emptyPassword) {
            emptyPassword.printStackTrace();
        } catch (UserEmpty userEmpty) {
            userEmpty.printStackTrace();
        }
    }
    public void changeScene3(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("welcomePage.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public void minimizeWindow(javafx.event.ActionEvent min) {
        Stage window = (Stage) ((Node)min.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    public void closeWindow(javafx.event.ActionEvent close) {
        Stage window = (Stage) ((Node)close.getSource()).getScene().getWindow();
        window.close();
    }
}
