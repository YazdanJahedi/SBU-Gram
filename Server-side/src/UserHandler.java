import Messages.ClientMessages.*;
import Messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserHandler implements Runnable {
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    String username = "USER";

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
            } catch (Exception e) {
                System.out.println( username +" closed the program");
                System.out.println("time : " +dateFormatter.format(LocalDateTime.now())+"\n");
                break;
            }


            Message answer = null;
            if (message instanceof LogInMessage) {
                username = ((LogInMessage) message).getUsername();
                answer = MessageHandler.loginHandler((LogInMessage) message);
            } else if (message instanceof SignUpMessage){
                username = ((SignUpMessage) message).getUsername();
                answer = MessageHandler.SignupHandler((SignUpMessage) message);
            } else if (message instanceof MakeResetPasswordPageMessage){
                username = ((MakeResetPasswordPageMessage) message).getUsername();
                answer = MessageHandler.makeResetPasswordPage((MakeResetPasswordPageMessage)message, username);
            } else if(message instanceof SendResetAnswerMessage){
                answer = MessageHandler.SendAnswerHandler((SendResetAnswerMessage) message , username);
            }


            try {
                out.writeObject(answer);
            } catch (IOException e) {
                System.err.println("CreateAccountMessage couldn't be sent!");
            }

        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
