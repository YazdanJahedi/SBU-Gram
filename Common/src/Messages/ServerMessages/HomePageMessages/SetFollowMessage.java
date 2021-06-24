package Messages.ServerMessages.HomePageMessages;

import Messages.Message;

public class SetFollowMessage implements Message {
    private final boolean isUserFollowed;

    public SetFollowMessage(boolean isUserFollowed) {
        this.isUserFollowed = isUserFollowed;
    }

    public boolean isUserFollowed() {
        return isUserFollowed;
    }
}
