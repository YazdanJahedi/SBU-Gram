import Messages.ClientMessages.LogInMessage;
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
//            Object o = null;

            String username = null;
            try {
//                o = in.readObject();
                username =(String) in.readObject();
                System.out.println("username is gotten");
            } catch (Exception e) {
                System.err.println("Error1");
            }

            System.out.println("here~~~~~~~~~");
//            if(o instanceof LogInMessage){
//                System.out.println("the message was : Login message.");

//                LogInMessage l = (LogInMessage)o;
            if (s.contains(username)) {
                try {
//                        out.writeObject(new FindUserMessage(true));
                    out.writeObject("true");
                    System.out.println("true message is sent");
                } catch (IOException e) {
                    System.err.println("Error2");
                }
            } else {
                try {
//                        out.writeObject(new FindUserMessage(false));
                    out.writeObject("false");
                    System.out.println("false message is sent");
                } catch (IOException e) {
                    System.err.println("Error3");
                }
            }
//            }
        }
    }
}
