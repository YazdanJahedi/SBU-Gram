package Messages.ServerMessages;

import Messages.Message;

public class CreateAccountMessage implements Message {
    private final boolean isUsernameValid;

    public CreateAccountMessage(boolean isUsernameInvalid) {
        this.isUsernameValid = isUsernameInvalid;
    }

    public boolean isUsernameInvalid() {
        return isUsernameValid;
    }
}
