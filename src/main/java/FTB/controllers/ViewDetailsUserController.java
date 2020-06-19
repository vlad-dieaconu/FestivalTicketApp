package FTB.controllers;

import FTB.model.Festival;
import FTB.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import FTB.controllers.LoginController;


import static FTB.services.FestivalService.FESTIVALS_PATH;
import static FTB.services.UserService.USERS_PATH;


import FTB.model.*;
import org.apache.commons.io.FileUtils;


public class ViewDetailsUserController extends User{

    @FXML
    private ListView<String> listView;

    LoginController l = new LoginController();
    ArrayList<String> ord=new ArrayList<String>();

    Button buyButton;
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
        readJsonData();
        for(int i=0;i<festivals.size();i++) {
            if(listView.getSelectionModel().getSelectedItem().equals(festivals.get(i).getFestivalName())){
                //System.out.println(festivals.get(i).getFestivalDetails());
                popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("More details");
                Label label1= new Label(festivals.get(i).getFestivalDetails());
                Button popupButton = new Button("Back to festivals");
                buyButton = new Button("BUY");
                buyButton.setOnAction(event -> {

                    try {
                        addOrder();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


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

    public void addOrder() throws IOException {


        String s;
        s=listView.getSelectionModel().getSelectedItem();

        System.out.println("click on buy " + s);

        List<User> useri;
        ArrayList<User> users2 = new ArrayList<User>();

        ObjectMapper objectMapper = new ObjectMapper();
        useri = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>(){});

        for(User item : useri ){
            users2.add(item);
        }
        for(User item2:users2){
            if(item2.getUsername().equals(LoginController.getUser())){
                if(item2.getAccBalance()>=100) {
                    ord = item2.getOrders();
                    ord.add(s);
                    item2.nrOrders++;
                    item2.setOrders(ord);
                    item2.ordersApproved.add(0);
                    item2.setAccBalance(item2.getAccBalance()-100);
                    if(item2.getNrOrders()==5){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"No more !", ButtonType.OK);
                        alert.showAndWait();
                    }

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Not enough money !", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }

        FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        ObjectMapper objMap = new ObjectMapper();
        objMap.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users2);

//        System.out.println(ord);
    }


    public void back(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("userView.fxml"));
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





