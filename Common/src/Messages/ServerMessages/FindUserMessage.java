package Messages.ServerMessages;

import Messages.Message;

public class FindUserMessage implements Message {
    private final boolean isUserFound;

    public FindUserMessage(boolean userFound) {
        this.isUserFound = userFound;
    }

    public boolean isUserFound() {
        return isUserFound;
    }
}
