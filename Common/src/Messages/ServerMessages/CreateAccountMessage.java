package Messages.ServerMessages;

import Messages.Message;

public class CreateAccountMessage implements Message {
    private final boolean isUsernameValid;

    public CreateAccountMessage(boolean isUsernameValid) {
        this.isUsernameValid = isUsernameValid;
    }

    public boolean isUsernameValid() {
        return isUsernameValid;
    }
}
