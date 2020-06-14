package FTB.controllers;


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
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;


import static FTB.services.UserService.USERS_PATH;

public class CustomerListController {

    @FXML
    private ListView<String> list;

    private static List<User> users;



        public void initialize() throws IOException {
            readJsonData();

            for(int i=1;i<users.size();i++){
            System.out.println("Usernames:"+ users.get(i).getUsername()+" and "+ "Emails:"+users.get(i).getEmail());
            list.getItems().add(users.get(i).getUsername());
            }
        }


    public void handleMouseClick() throws IOException {
        Stage popupWindow;
        readJsonData();
        for(User item : users ){
            if(list.getSelectionModel().getSelectedItem().equals(item.getUsername())){
                popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Users Orders");
                ListView listView = new ListView();

                listView.setItems(init());
              //  listView.setEditable(true);

                //listView.setCellFactory(TextFieldListCell.forListView());



                Button popupButton = new Button("Back to users list");
                Stage finalPopupWindow = popupWindow;
                popupButton.setOnAction(e -> finalPopupWindow.close());

                VBox layout= new VBox(20);
                layout.getChildren().addAll(listView, popupButton);
                layout.setAlignment(Pos.CENTER);
                Scene scene1= new Scene(layout);
                popupWindow.setScene(scene1);
                popupWindow.showAndWait();
                }
        }
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


}
