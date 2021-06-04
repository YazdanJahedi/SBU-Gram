import Messages.ClientMessages.LogInMessage;
import Messages.ClientMessages.SignUpMessage;
import Messages.Message;
import Messages.ServerMessages.CreateAccountMessage;
import Messages.ServerMessages.FindUserMessage;

public class MessageHandler {
    static DataBase dataBase = DataBase.getInstance();


    public static Message loginHandler(LogInMessage logInMessage) {
        System.out.println("the message was : Login message");

        if (dataBase.getData().containsKey(logInMessage.getUsername())) {
            System.out.println("this username exists in the server!");
            String password = dataBase.getData().get(logInMessage.getUsername()).getPassword();

            if (password.equals(logInMessage.getPassword())) {
                System.out.println("the password is correct");
                System.out.println("--------------------");
                return new FindUserMessage(true);
            }
        }
        System.out.println("username or password is not correct!");
        System.out.println("--------------------");
        return new FindUserMessage(false);
    }


    public static Message SignupHandler(SignUpMessage signUpMessage) {
        System.out.println("the message was : SignUP message");
        CreateAccountMessage answer = new CreateAccountMessage();

        if (dataBase.getData().containsKey(signUpMessage.getUsername())) {
            System.out.println("username already exists");
            answer.setUsernameValid(false);
        }
        if (signUpMessage.getPassword().length() < 8) {
            System.out.println("password is invalid");
            answer.setPasswordValid(false);
        }
        if (!signUpMessage.getPassword().equals(signUpMessage.getConfirmPassword())){
            System.out.println("confirm password is incorrect");
            answer.setConfirmPasswordValid(false);
        }

        if (answer.isPasswordValid() && answer.isUsernameValid() && answer.isConfirmPasswordValid()) {
            dataBase.getData().
                    put(signUpMessage.getUsername(),
                            new User(signUpMessage.getUsername(),
                                    signUpMessage.getPassword(), signUpMessage.getTheQuestion()
                                    , signUpMessage.getAnswerOfTheQuestion()));

            System.out.println(signUpMessage.getUsername() + " 's account is created!");
        }
        System.out.println("------------------");
        return answer;
    }
}
