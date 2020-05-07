package FTB.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeScene(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public void changeScene2(ActionEvent event) throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }
}