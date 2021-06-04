package Messages.ServerMessages;

import Messages.Message;

public class FindUserMessage implements Message {
    private final boolean isUserFound;

    public FindUserMessage(boolean isUserFound) {
        this.isUserFound = isUserFound;
    }

    public boolean isUserFound() {
        return isUserFound;
    }
}
