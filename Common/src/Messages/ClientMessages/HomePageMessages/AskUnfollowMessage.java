package Messages.ClientMessages.HomePageMessages;

public class AskUnfollowMessage {
    private final String username;

    public AskUnfollowMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
