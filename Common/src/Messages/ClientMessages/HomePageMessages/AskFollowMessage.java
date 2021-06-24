package Messages.ClientMessages.HomePageMessages;

import Messages.Message;

public class AskFollowMessage implements Message {
    private final String username;

    public AskFollowMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
