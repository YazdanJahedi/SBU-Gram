import Messages.ClientMessages.*;
import Messages.Message;
import Messages.ServerMessages.FindUserMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserHandler implements Runnable {
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public UserHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {

            Message message = null;
            try {
                message = (Message) in.readObject();
                System.out.println("Message is gotten");
            } catch (Exception e) {
                System.err.println("Message couldn't be gotten");
            }

            if (message instanceof LogInMessage) {

                Message answer = MessageHandler.loginHandler((LogInMessage) message);

                try {
                    out.writeObject(answer);
                } catch (IOException e) {
                    System.err.println("true FindUserMessage couldn't be sent!");
                }

            }
        }
    }
}
