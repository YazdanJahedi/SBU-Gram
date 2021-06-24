package Messages.ServerMessages.HomePageMessages;

import Messages.Message;

public class SetUnfollowMessage implements Message {
    private final boolean isUserUnfollowed;

    public SetUnfollowMessage(boolean isUserFollowed) {
        this.isUserUnfollowed = isUserFollowed;
    }

    public boolean isUserUnfollowed() {
        return isUserUnfollowed;
    }
}
