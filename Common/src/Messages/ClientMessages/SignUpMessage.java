package Messages.ClientMessages;

import Messages.Message;

public class SignUpMessage implements Message {
    private final String username;
    private final String password;
    private final String answerOfTheQuestion;

    public SignUpMessage(String username, String password , String answerOfTheQuestion) {
        this.username = username;
        this.password = password;
        this.answerOfTheQuestion = answerOfTheQuestion;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAnswerOfTheQuestion() {
        return answerOfTheQuestion;
    }
}
