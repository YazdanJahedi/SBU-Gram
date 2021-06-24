package Messages.ServerMessages.PostItemMessages;

import Messages.Message;

public class SetRepostMessage implements Message {
    private final boolean isReposted;

    public SetRepostMessage(boolean isReposted) {
        this.isReposted = isReposted;
    }

    public boolean isReposted() {
        return isReposted;
    }
}
