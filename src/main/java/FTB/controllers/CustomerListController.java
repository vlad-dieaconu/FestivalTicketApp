package FTB.controllers;


import FTB.model.User;
import FTB.services.UserService;
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
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static FTB.services.UserService.USERS_PATH;

public class CustomerListController {

    @FXML
    private ListView<String> list;

    private static List<User> users;

    ListView listView = new ListView();



        public void initialize() throws IOException {
            readJsonData();

            for(int i=1;i<users.size();i++){
            System.out.println("Usernames:"+ users.get(i).getUsername()+" and "+ "Emails:"+users.get(i).getEmail());
            list.getItems().add(users.get(i).getUsername());
            }
        }


    public void handleMouseClick() throws IOException {
        Stage popupWindow=new Stage();
        readJsonData();
        for(User item : users ){
            if(list.getSelectionModel().getSelectedItem().equals(item.getUsername())){
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("User Orders");


                listView.setItems(init());

                Button popupButton = new Button("Back to users list");
                Stage finalPopupWindow = popupWindow;
                popupButton.setOnAction(e->finalPopupWindow.close());

                VBox layout = new VBox(20);


                HBox hbox = new HBox(10);
                Button allowButton = new Button("Allow");
                Button denyButton = new Button("Deny");

                allowButton.setOnAction(e->{
                    try {
                        accept();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                denyButton.setOnAction(e->{
                    try {
                        deny();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                hbox.getChildren().addAll(popupButton, allowButton, denyButton);

                layout.getChildren().addAll(listView, hbox);

                layout.setAlignment(Pos.CENTER);
                Scene scene1 = new Scene(layout);

                popupWindow.setScene(scene1);
                popupWindow.showAndWait();
                }
        }
     }


    public void accept() throws IOException {

        List<User> useri;
        ArrayList<User> users2 = new ArrayList<User>();
        ObjectMapper objectMapper = new ObjectMapper();
        useri = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>(){});

        for(User item : useri ){
            users2.add(item);
        }

        for(User item2 : users2){
            if(list.getSelectionModel().getSelectedItem().equals(item2.getUsername())) {

                int index;
                index = listView.getSelectionModel().getSelectedIndex();
                item2.setApproved(index);
                System.out.println(item2.getOrdersApproved());


            }
        }

        FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        ObjectMapper objMap = new ObjectMapper();
        objMap.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users2);

    }

     public void deny() throws IOException {

         List<User> useri;
         ArrayList<User> users2 = new ArrayList<User>();
         ObjectMapper objectMapper = new ObjectMapper();
         useri = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>(){});

         for(User item : useri ){
             users2.add(item);
         }

         for(User item2 : users2){
             if(list.getSelectionModel().getSelectedItem().equals(item2.getUsername())) {

                 int index;
                 index = listView.getSelectionModel().getSelectedIndex();
                 item2.setDeny(index);
                 System.out.println(item2.getOrdersApproved());


             }
         }
         FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
         ObjectMapper objMap = new ObjectMapper();
         objMap.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users2);
     }


    public ObservableList<String> init(){
       ObservableList<String> items= FXCollections.observableArrayList();

        for(int i=0;i<users.size();i++){

            if(list.getSelectionModel().getSelectedItem().equals(users.get(i).getUsername())){

                   for (int j=0;j<users.get(i).getOrders().size();j++){
                    items.add(users.get(i).getCurrentOrder(j));

                   }
            }
        }
        return items;
    }

    public void readJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(),
                new TypeReference<List<User>>() {
                });

    }

    public void backToAdminView(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("adminView.fxml"));
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
