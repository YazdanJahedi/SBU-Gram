import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server is started!");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("* a new user is connected to the server!\n");
            new Thread(new UserHandler(socket)).start();
        }
    }
}
