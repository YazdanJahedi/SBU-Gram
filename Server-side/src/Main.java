import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private final static int PORT = 8080;

    public static void main(String[] args)  {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("~ ERROR: server socket can not be opened");
            System.err.println("~ Please try again to open the server");
            return;
        }

        System.out.println("*** the server socket is opened!\n\n");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("~ server couldn't accept a client");
            }

            System.out.println("* a new client is connected to the server!\n");

            assert socket != null;
            new Thread(new UserHandler(socket)).start();
        }
    }
}
