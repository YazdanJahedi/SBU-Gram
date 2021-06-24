package Messages.ServerMessages.HomePageMessages;

public class SetFollowMessage {
    private final boolean isUserFollowed;

    public SetFollowMessage(boolean isUserFollowed) {
        this.isUserFollowed = isUserFollowed;
    }

    public boolean isUserFollowed() {
        return isUserFollowed;
    }
}
