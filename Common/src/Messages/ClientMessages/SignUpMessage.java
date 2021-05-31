package Messages.ClientMessages;

import Messages.Message;

public class SignUpMessage implements Message {
    String username;
    String password;

    public SignUpMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
