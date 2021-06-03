import Messages.ClientMessages.*;
import Messages.Message;
import Messages.ServerMessages.FindUserMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class UserHandler implements Runnable {
    // DataBase dataBase;
    Set<String> s = DataBase.s;
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
//asdasdfasdfasdfasdfasdfasdfasdfasdf

            Message message = null;
            try {
                message = (Message) in.readObject();
                System.out.println("Message is gotten");
            } catch (Exception e) {
                System.err.println("Message couldn't be gotten");
            }

            if (message instanceof LogInMessage) {
                System.out.println("the message was : Login message.");

                LogInMessage logInMessage = (LogInMessage) message;
                if (s.contains(logInMessage.getUsername())) {
                    try {
                        out.writeObject(new FindUserMessage(true));
                        System.out.println("user is found!");
                    } catch (IOException e) {
                        System.err.println("true FindUserMessage couldn't be sent!");
                    }
                } else {
                    try {
                        out.writeObject(new FindUserMessage(false));
                        System.out.println("~ user not found!");
                    } catch (IOException e) {
                        System.err.println("false FindUserMessage couldn't be sent!");
                    }
                }
            }
        }
    }
}
