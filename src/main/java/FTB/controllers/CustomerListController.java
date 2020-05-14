package FTB.controllers;


import FTB.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


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


}
