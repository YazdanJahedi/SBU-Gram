package Model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Main extends Application {
    private final static int PORT = 8080;
    private final static String HOST_IP = "127.0.0.1";

    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    static {
        try {
            socket = new Socket(HOST_IP , PORT);
            System.out.println("* you connected to server successfully\n");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("~ ERROR: your connection to the server is failed");
        }
    }


    public static Socket getSocket() {
        return socket;
    }

    public static ObjectInputStream getObjectInputStream() {
        return in;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return out;
    }

    //--------------------------------------------------------------------

    @Override
    public void start(Stage stage) {
        // all scenes will be loaded on this stage
        PageLoader.initStage(stage);

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
        launch(args);
    }

}
