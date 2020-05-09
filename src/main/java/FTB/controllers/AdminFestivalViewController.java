package FTB.controllers;


import FTB.services.FestivalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.awt.*;
import java.io.IOException;
import FTB.model.Festival;



public class AdminFestivalViewController {

    @FXML
    private  TextField Nume;
    @FXML
    private  TextArea Detalii;




    @FXML
    public void handleAddFestAction() {


            FestivalService.addFestivals(Nume.getText(), Detalii.getText());

    }


    public void backTologin(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }


}
