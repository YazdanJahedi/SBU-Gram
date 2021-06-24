package Messages.ServerMessages.HomePageMessages;

public class SetUnfollowMessage {
    private final boolean isUserFollowed;

    public SetUnfollowMessage(boolean isUserFollowed) {
        this.isUserFollowed = isUserFollowed;
    }

    public boolean isUserFollowed() {
        return isUserFollowed;
    }
}
