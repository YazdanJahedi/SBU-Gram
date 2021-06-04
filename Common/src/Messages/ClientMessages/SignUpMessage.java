package Messages.ClientMessages;

import Messages.Message;

public class SignUpMessage implements Message {
    private final String username;
    private final String password;
    private final String confirmPassword;
    private final String theQuestion;
    private final String answerOfTheQuestion;

    public SignUpMessage(String username, String password ,String confirmPassword ,String theQuestion , String answerOfTheQuestion) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.answerOfTheQuestion = answerOfTheQuestion;
        this.theQuestion = theQuestion;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public String getAnswerOfTheQuestion() {
        return answerOfTheQuestion;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
