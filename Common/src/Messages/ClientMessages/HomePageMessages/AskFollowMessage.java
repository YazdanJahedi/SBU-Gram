package Messages.ClientMessages.HomePageMessages;

public class AskFollowMessage {
    private final String username;

    public AskFollowMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
