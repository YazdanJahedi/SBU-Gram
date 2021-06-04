import Messages.ClientMessages.LogInMessage;
import Messages.ClientMessages.SignUpMessage;
import Messages.Message;
import Messages.ServerMessages.CreateAccountMessage;
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

    public static Message SignupHandler(SignUpMessage signUpMessage) {
        if (dataBase.getData().containsKey(signUpMessage.getUsername())) {
            return new CreateAccountMessage(false);
        }
        dataBase.getData().
                put(signUpMessage.getUsername(),
                        new User(signUpMessage.getUsername(),
                                signUpMessage.getPassword(), signUpMessage.getTheQuestion()
                                    ,signUpMessage.getAnswerOfTheQuestion()));

        System.out.println(signUpMessage.getUsername() + " 's account is created!");
        return new CreateAccountMessage(true);
    }
}
