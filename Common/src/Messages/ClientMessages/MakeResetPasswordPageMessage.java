package Messages.ClientMessages;

import Messages.Message;

public class MakeResetPasswordPageMessage implements Message {
    private final String username;

    public MakeResetPasswordPageMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
