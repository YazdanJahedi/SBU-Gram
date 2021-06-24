package Messages.ClientMessages.HomePageMessages;

import Messages.Message;

public class AskUnfollowMessage implements Message {
    private final String username;

    public AskUnfollowMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
