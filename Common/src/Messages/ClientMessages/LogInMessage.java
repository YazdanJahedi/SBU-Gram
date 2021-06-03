package Messages.ClientMessages;

import Messages.Message;

public class LogInMessage implements Message {
    private final String username;
    private final String password;

    public LogInMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
