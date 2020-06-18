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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    public void handleMouseClick() throws IOException {
        Stage popupWindow=new Stage();
        readJsonData();


        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Status");
        Button popupButton = new Button("Back to orders list");
        Stage finalPopupWindow = popupWindow;

        popupButton.setOnAction(e->finalPopupWindow.close());
        VBox layout = new VBox(20);

        HBox hbox = new HBox(10);

        Button statusButton = new Button("Status");
        statusButton.setOnAction(e->{getStatus();});

        hbox.getChildren().addAll(popupButton, statusButton);
        layout.getChildren().addAll(hbox);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout,500,500);
        popupWindow.setScene(scene1);
        popupWindow.showAndWait();
    }



    public void getStatus(){
        String status="";
        int index = 0;
        for(User item:users) {
            if (item.getUsername().equals(LoginController.getUser())) {
                //System.out.println("intrat1");

                index =list.getSelectionModel().getSelectedIndex();


                if (item.getOrdersApproved().get(index) == 2) {
                    status = "Your order is approved";

                } else if (item.getOrdersApproved().get(index) == 1) {
                    status = "Your order was deny";
                } else if (item.getOrdersApproved().get(index) == 0) {
                    status = "Not reviewed yet";
                }

                Stage popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Status");
                Label label = new Label();
                label.setText(status);
                Button popupButton = new Button("Back to orders list");
                Stage finalPopupWindow = popupWindow;
                popupButton.setOnAction(e -> finalPopupWindow.close());
                HBox hbox = new HBox(10);
                VBox layout = new VBox(50);
                hbox.getChildren().addAll(popupButton);

                layout.getChildren().addAll(hbox, label);

                layout.setAlignment(Pos.CENTER);
                Scene scene1 = new Scene(layout,500,500);

                popupWindow.setScene(scene1);
                popupWindow.showAndWait();

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
