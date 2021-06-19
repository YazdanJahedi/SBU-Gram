package Messages.ServerMessages;

import Messages.Message;

public class IsProfileChangedMessage implements Message {
    private final boolean isProfileChanged ;

    public IsProfileChangedMessage(boolean isProfileChanged) {
        this.isProfileChanged = isProfileChanged;
    }

    public boolean isProfileChanged() {
        return isProfileChanged;
    }
}
