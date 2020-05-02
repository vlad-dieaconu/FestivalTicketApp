package FTB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import FTB.services.UserService;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Registration.fxml"));
        primaryStage.setTitle("Register");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}