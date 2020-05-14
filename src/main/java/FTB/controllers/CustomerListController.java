package FTB.controllers;


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


import static FTB.services.UserService.USERS_PATH;

public class CustomerListController {

    @FXML
    private ListView<String> list;

    private static List<User> users;



        public void initialize() throws IOException {
            readJsonData();

            for(int i=0;i<users.size();i++){
            System.out.println("Usernames:"+ users.get(i).getUsername()+" and "+ "Emails:"+users.get(i).getEmail());
            list.getItems().add("User"+i+": "+users.get(i).getUsername()+" and "+"Email: "+users.get(i).getEmail());
            }
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
