package Model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    private static Socket socket = null;

    public static Socket getSocket() {
        return socket;
    }

    public static ObjectInputStream getObjectInputStream() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("MAIN :: getObject\"In\"putStream done well");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("MAIN :: getObject\"OUT\"putStream done well");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    //--------------------------------------------------------------------

    @Override
    public void start(Stage stage) {
        PageLoader.initStage(stage); //this is only needed when you start program
        //and need a new stage. all scenes will be loaded on this stage
        try {
            new PageLoader().load("LoginPage");
        } catch (IOException e) {
            System.err.println("~ log in page wasn't found!");
        }
    }

    @Override
    // this method happens when the program is opened
    public void init() {
        System.out.println("program opened");
    }

    @Override
    // this method happens when the program is closed
    public void stop() {
        System.out.println("program closed");
    }

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 8080);
            System.out.println("* connected to server!");
        } catch (IOException e) {
            System.err.println("~ Client couldn't connect to the server");
            return;
        }
        launch(args);
    }
}
