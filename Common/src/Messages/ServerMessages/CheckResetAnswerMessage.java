package Messages.ServerMessages;

import Messages.Message;

public class CheckResetAnswerMessage implements Message {
    private final boolean isAnswerTrue;
    private final String password;

    public CheckResetAnswerMessage(boolean isAnswerTrue, String password) {
        this.isAnswerTrue = isAnswerTrue;
        this.password = password;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

    public String getPassword() {
        return password;
    }

}
