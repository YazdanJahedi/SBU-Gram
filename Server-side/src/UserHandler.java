import Messages.ClientMessages.LogInMessage;
import Messages.ServerMessages.FindUserMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class UserHandler implements Runnable{
    // DataBase dataBase;
    Set<String> s= DataBase.s;
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
        while(!socket.isClosed()){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = null;
            try {
                o = in.readObject();
            } catch (Exception e) {
                System.err.println("Error1");
            }

            if(o instanceof LogInMessage){
                LogInMessage l = (LogInMessage)o;
                if(s.contains(l.username)){
                    try {
                        out.writeObject(new FindUserMessage(true));
                    } catch (IOException e) {
                        System.err.println("Error2");
                    }
                } else {
                    try {
                        out.writeObject(new FindUserMessage(false));
                    } catch (IOException e) {
                        System.err.println("Error3");
                    }
                }
            }
        }
    }
}
