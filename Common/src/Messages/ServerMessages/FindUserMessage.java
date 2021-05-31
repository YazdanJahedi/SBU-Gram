package Messages.ServerMessages;

import Messages.Message;

public class FindUserMessage implements Message {
    boolean userFound;

    public FindUserMessage(boolean userFound) {
        this.userFound = userFound;
    }

    public boolean isUserFound() {
        return userFound;
    }
}
