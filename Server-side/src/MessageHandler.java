import Messages.ClientMessages.LogInMessage;
import Messages.Message;
import Messages.ServerMessages.FindUserMessage;

public class MessageHandler {
    static DataBase dataBase = DataBase.getInstance();


    public static Message loginHandler(LogInMessage logInMessage) {
        System.out.println("the message was : Login message.");

        if (dataBase.getData().containsKey(logInMessage.getUsername())) {
            String password = dataBase.getData().get(logInMessage.getUsername()).getPassword();

            if (password.equals(logInMessage.getPassword())) {
                System.out.println("user is found!");
                return new FindUserMessage(true);
            }
        }
        return new FindUserMessage(false);
    }
}
