package FTB;

import FTB.model.Festival;
import FTB.services.FestivalService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import FTB.services.UserService;
import javafx.stage.StageStyle;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();
        FestivalService.loadFestivalsFromFile();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcomePage.fxml"));
        primaryStage.setTitle("Festival Tickets App");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    
}
