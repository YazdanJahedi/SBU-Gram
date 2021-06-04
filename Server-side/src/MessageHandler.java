import Messages.ClientMessages.LogInMessage;
import Messages.ClientMessages.MakeResetPasswordPageMessage;
import Messages.ClientMessages.SendResetAnswerMessage;
import Messages.ClientMessages.SignUpMessage;
import Messages.Message;
import Messages.ServerMessages.CheckResetAnswerMessage;
import Messages.ServerMessages.CreateAccountMessage;
import Messages.ServerMessages.FindUserMessage;
import Messages.ServerMessages.SendResetQuestionMessage;

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

    public static Message makeResetPasswordPage(MakeResetPasswordPageMessage makeResetPasswordPageMessage , String username){
        if (dataBase.getData().containsKey(makeResetPasswordPageMessage.getUsername()))
            return new SendResetQuestionMessage(true , dataBase.getData().get(username).theQuestion);

        return new SendResetQuestionMessage(false , "");
    }

    public static Message SendAnswerHandler(SendResetAnswerMessage sendResetAnswerMessage , String username){
        if (sendResetAnswerMessage.getAnswer().equals(dataBase.getData().get(username).getAnswerOfTheQuestion()))
            return new CheckResetAnswerMessage(true , dataBase.getData().get(username).getPassword());

        return new CheckResetAnswerMessage(false , "");
    }
}
