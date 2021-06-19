import Messages.Message;
import Messages.ClientMessages.*;
import Messages.ServerMessages.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageHandler {
    static DataBase dataBase = DataBase.getInstance();
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static String closeMessage(String username) {
        String closeMessage = "";
        closeMessage += "* " + username + " closed the program";
        closeMessage += "time : " + dateFormatter.format(LocalDateTime.now()) + "\n";
        return closeMessage;
    }

    public static Message loginHandler(LogInMessage logInMessage) {
        System.out.println(logInMessage.getUsername() + " wants to login ...");

        if (dataBase.getData().containsKey(logInMessage.getUsername())) {
            String password = dataBase.getData().get(logInMessage.getUsername()).getPassword();

            if (password.equals(logInMessage.getPassword())) {
                System.out.println(logInMessage.getUsername() + " is logged in");
                System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
                return new FindUserMessage(true);
            }
        }
        System.out.println(logInMessage.getUsername() + " 's username or password is incorrect");
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        return new FindUserMessage(false);
    }

    public static Message SignupHandler(SignUpMessage signUpMessage) {
        System.out.println(signUpMessage.getUsername() + " wants to Sign up...");
        CreateAccountMessage answer = new CreateAccountMessage();

        if (dataBase.getData().containsKey(signUpMessage.getUsername())) {
            System.out.println("username already exists");
            answer.setUsernameValid(false);
        }
        if (signUpMessage.getPassword().length() < 8) {
            System.out.println("password is invalid");
            answer.setPasswordValid(false);
        }
        if (!signUpMessage.getPassword().equals(signUpMessage.getConfirmPassword())) {
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
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
        return answer;
    }

    public static Message makeResetPasswordPage(MakeResetPasswordPageMessage makeResetPasswordPageMessage, String username) {
        if (dataBase.getData().containsKey(makeResetPasswordPageMessage.getUsername()))
            return new SendResetQuestionMessage(true, dataBase.getData().get(username).theQuestion);

        return new SendResetQuestionMessage(false, "");
    }

    public static Message SendAnswerHandler(SendResetAnswerMessage sendResetAnswerMessage, String username) {
        if (sendResetAnswerMessage.getAnswer().equals(dataBase.getData().get(username).getAnswerOfTheQuestion()))
            return new CheckResetAnswerMessage(true, dataBase.getData().get(username).getPassword());

        return new CheckResetAnswerMessage(false, "");
    }

    public static Message changeProfileHandler(ChangeProfileMessage changeProfileMessage, String username) {
        User user = dataBase.getData().get(username);
        if (user != null) {
            user.setFirstName(changeProfileMessage.getFirstName());
            user.setLastName(changeProfileMessage.getLastName());
            user.setBio(changeProfileMessage.getBio());
            user.setBirthDate(changeProfileMessage.getBirthDate());
            user.setProfileImage(changeProfileMessage.getProfileImage());
            System.out.println(username + " changed his/her profile information");
            System.out.println("first name : \"" + changeProfileMessage.getFirstName() + "\"");
            System.out.println("last name : \"" + changeProfileMessage.getLastName() + "\"");
            System.out.println("bio : \"" + changeProfileMessage.getBio() + "\"");
            System.out.println("birth date : \"" + changeProfileMessage.getBirthDate() + "\"");
            System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
            return new IsProfileChangedMessage(true);
        }
        System.out.println(username + " 's try to change his/her profile is failed");
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
        return new IsProfileChangedMessage(false);
    }
}
