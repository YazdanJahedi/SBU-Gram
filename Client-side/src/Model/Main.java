package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        PageLoader.initStage(stage); //this is only needed when you start program
        //and need a new stage. all scenes will be loaded on this stage
        try {
            new PageLoader().load("loginPage");
        } catch (IOException e) {
            System.err.println("~ page wasn't found!");
        }
    }

    @Override
    //this function happens when the program is opened
    public void init() {
        System.out.println("program opened");
    }

    @Override
    //this function happens when the program is closed
    public void stop() {
        System.out.println("program closed");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
