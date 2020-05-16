package FTB.controllers;

import FTB.model.Festival;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


import static FTB.services.FestivalService.FESTIVALS_PATH;
import static FTB.services.UserService.USERS_PATH;

public class ViewDetailsUserController {

    @FXML
    private ListView<String> listView;

    private static List<Festival> festivals;

    public void initialize() throws IOException {
        readJsonData();
        for(int i=0;i<festivals.size();i++) {
            //System.out.println("Festival name:" + festivals.get(i).getFestivalName() + "\n " + "Festival details: " + festivals.get(i).getFestivalDetails());
            listView.getItems().add( festivals.get(i).getFestivalName());
        }
    }

    public void handleMouseClick() throws IOException {
        Stage popupWindow;
        Parent root;
        readJsonData();
        for(int i=0;i<festivals.size();i++) {
            if(listView.getSelectionModel().getSelectedItem().equals(festivals.get(i).getFestivalName())){
                //System.out.println(festivals.get(i).getFestivalDetails());
                popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("More details");
                Label label1= new Label(festivals.get(i).getFestivalDetails());
                Button popupButton = new Button("Back to festivals");
                Button buyButton = new Button("BUY");
                Stage finalPopupWindow = popupWindow;
                popupButton.setOnAction(e -> finalPopupWindow.close());
                VBox layout= new VBox(10);
                layout.getChildren().addAll(label1, buyButton, popupButton);
                layout.setAlignment(Pos.CENTER);
                Scene scene1= new Scene(layout, 300, 250);
                popupWindow.setScene(scene1);
                popupWindow.showAndWait();

            }

        }
        //System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
    }

    public void readJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        festivals = objectMapper.readValue(FESTIVALS_PATH.toFile(),
                new TypeReference<List<Festival>>() {
                });
    }

    public void back(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("userView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    }



