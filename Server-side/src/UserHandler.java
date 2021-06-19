import Messages.ClientMessages.*;
import Messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class UserHandler implements Runnable {
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    String username = "USER";

    
    public UserHandler(Socket socket) {
        this.socket = socket;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("~ ERROR: the server can't make streams to the client side");
        }
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {

            Message message;
            try {
                message = (Message) in.readObject();
            } catch (Exception e) {
                System.out.println(MessageHandler.closeMessage(username));
                break;
            }


            Message answer = null;
            if (message instanceof LogInMessage) {
                username = ((LogInMessage) message).getUsername();
                answer = MessageHandler.loginHandler((LogInMessage) message);
            } else if (message instanceof SignUpMessage) {
                username = ((SignUpMessage) message).getUsername();
                answer = MessageHandler.SignupHandler((SignUpMessage) message);
            } else if (message instanceof MakeResetPasswordPageMessage) {
                username = ((MakeResetPasswordPageMessage) message).getUsername();
                answer = MessageHandler.makeResetPasswordPage((MakeResetPasswordPageMessage) message, username);
            } else if (message instanceof SendResetAnswerMessage) {
                answer = MessageHandler.SendAnswerHandler((SendResetAnswerMessage) message, username);
            } else if (message instanceof ChangeProfileMessage) {
                answer = MessageHandler.changeProfileHandler((ChangeProfileMessage) message, username);
            }


            try {
                out.writeObject(answer);
            } catch (IOException e) {
                System.err.println("~ERROR: the server's answer is not sent to the client");
            }

        }

        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("~ ERROR: the server-side socket of " + username + " can't be closed");
        }
    }
}
