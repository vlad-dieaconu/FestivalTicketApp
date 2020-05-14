package FTB.controllers;

import FTB.model.Festival;
import FTB.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


import static FTB.services.FestivalService.FESTIVALS_PATH;

public class EditFestivalDetailsController {

   @FXML
   ListView<String> list;

    private static List<Festival> festivals;



    public void initialize() throws IOException {
        readJsonData();

        for(int i=0;i<festivals.size();i++){
            System.out.println("Title:"+ festivals.get(i).getFestivalName());
            list.getItems().add("Title"+i+": "+festivals.get(i).getFestivalName());
        }
    }



    public void readJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        festivals = objectMapper.readValue(FESTIVALS_PATH.toFile(),
                new TypeReference<List<Festival>>() {
                });
    }
    public void backToAdminView(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("adminView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }


}
