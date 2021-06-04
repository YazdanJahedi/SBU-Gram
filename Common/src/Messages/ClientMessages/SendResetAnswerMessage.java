package Messages.ClientMessages;

import Messages.Message;

public class SendResetAnswerMessage implements Message {
    private final String answer;

    public SendResetAnswerMessage(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
