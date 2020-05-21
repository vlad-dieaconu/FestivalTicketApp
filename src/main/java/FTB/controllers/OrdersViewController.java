package FTB.controllers;

import FTB.model.Festival;
import FTB.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static FTB.services.UserService.USERS_PATH;

public class OrdersViewController extends User {

    @FXML
    private ListView<String> list;

    private static List<User> users;
    private static List<Festival> festivals;


    public void initialize() throws IOException {
        readJsonData();
        for(User item : users){
          //  System.out.println("for1");
            if(item.getUsername().equals(LoginController.getUser())){
               // System.out.println("if1");
                ObservableList<String> items=FXCollections.observableArrayList();

                for (int i=0;i<item.getOrders().size();i++){

                  //  System.out.println("for2");
                    items.add(item.getCurrentOrder(i));

                    }

               list.setItems(items);
            }
        }
    }


    public void readJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(),
                new TypeReference<List<User>>() {
                });
    }















    public void backToUser(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("userView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }
}
