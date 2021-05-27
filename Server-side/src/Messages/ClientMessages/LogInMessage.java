package Messages.ClientMessages;

import Messages.Message;

public class LogInMessage implements Message {
    public String username;
    String password;

    public LogInMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
