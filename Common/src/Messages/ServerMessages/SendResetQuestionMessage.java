package Messages.ServerMessages;

import Messages.Message;

public class SendResetQuestionMessage implements Message {
    private final boolean isUserFound;
    private final String theQuestion;

    public SendResetQuestionMessage(boolean isUserFound, String theQuestion) {
        this.isUserFound = isUserFound;
        this.theQuestion = theQuestion;
    }

    public boolean isUserFound() {
        return isUserFound;
    }

    public String getTheQuestion() {
        return theQuestion;
    }
}
