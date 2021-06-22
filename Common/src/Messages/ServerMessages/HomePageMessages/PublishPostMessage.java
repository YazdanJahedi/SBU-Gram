package Messages.ServerMessages.HomePageMessages;

import Messages.Message;

public class PublishPostMessage implements Message {
    private final boolean isPostPublished;

    public PublishPostMessage(boolean isPostPublished) {
        this.isPostPublished = isPostPublished;
    }

    public boolean isPostPublished() {
        return isPostPublished;
    }
}
