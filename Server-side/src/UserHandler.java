import Messages.ClientMessages.*;
import Messages.ClientMessages.HomePageMessages.*;
import Messages.ClientMessages.PostItemMessages.AskAddCommentMessage;
import Messages.ClientMessages.PostItemMessages.AskRepostMessage;
import Messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class UserHandler implements Runnable {
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

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
                MessageHandler.closeMessage(username);
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
            } else if (message instanceof AskSetProfileInformationMessage) {
                answer = MessageHandler.setProfileInformation(username);
            } else if (message instanceof AskPublishPostMessage) {
                answer = MessageHandler.setPublishedPost((AskPublishPostMessage) message, username);
            } else if (message instanceof AskSearchMessage) {
                answer = MessageHandler.setSearchedProfileInformation((AskSearchMessage) message, username);
            } else if (message instanceof AskFollowMessage) {
                answer = MessageHandler.handleFollow((AskFollowMessage) message, username);
            } else if (message instanceof AskUnfollowMessage) {
                answer = MessageHandler.handleUnfollow((AskUnfollowMessage) message, username);
            } else if (message instanceof AskTimeLinePostsMessage){
                answer = MessageHandler.setTimeLinePosts(username);
            } else if (message instanceof AskRepostMessage){
                answer = MessageHandler.handleRepost((AskRepostMessage) message , username);
            } else if (message instanceof AskAddCommentMessage){
                answer = message;
                System.out.println("answer is sent :)");
            }


            try {
                out.writeObject(answer);
                out.flush();
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
