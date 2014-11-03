package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Dialogs.create()
                .owner(primaryStage)
                .title("Information Dialog")
                .masthead("Look, an Information Dialog")
                .message("I have a great message for you!")
                .showInformation();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
