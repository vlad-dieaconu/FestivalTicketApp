
package FTB.controllers;

import FTB.exceptions.EmptyPassword;
import FTB.exceptions.InvalidPassword;
import FTB.exceptions.InvalidUsername;
import FTB.exceptions.UserEmpty;
import FTB.services.UserService;
import javafx.event.ActionEvent;
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

import java.io.IOException;

public class LoginController {
    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField user_input;

    public static String user;

    @FXML
    public void handleLoginButtonAction(ActionEvent event) throws InvalidPassword, EmptyPassword, UserEmpty, InvalidUsername, IOException {
        try {
            UserService.checkUser(user_input.getText(), passwordField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Login reusit !", ButtonType.OK);
            loginMessage.setText("Login reusit !");
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();

            if(user_input.getText().equals("admin") && passwordField.getText().equals("admin")){

                Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("adminView.fxml"));
                Scene view2 = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(view2);
                window.show();
            }
            else{
                Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("userView.fxml"));
                Scene view2 = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(view2);
                window.show();
            }

            user = user_input.getText();




    } catch (InvalidUsername e) {
            loginMessage.setText("Your username is incorrect");
          Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
           alert.showAndWait();
           if(alert.getResult()==ButtonType.OK)
              alert.close();

        } catch (InvalidPassword e) {
            loginMessage.setText("Your password is wrong!");
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
              alert.close();
        } catch (EmptyPassword e) {
            loginMessage.setText("Please enter a password!");
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        } catch (UserEmpty e) {
            loginMessage.setText("Please enter an username!");
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
             alert.close();
        }

    }
    public void backToWelcome(ActionEvent event) throws IOException{
        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("welcomePage.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public static String getUser(){
        return user;
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