package FTB.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminViewController {



    public void backTologin(ActionEvent event) throws IOException {

            Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene view2 = new Scene(view);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(view2);
            window.show();
        }



}
