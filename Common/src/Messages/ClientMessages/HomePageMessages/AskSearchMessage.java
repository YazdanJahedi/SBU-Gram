package Messages.ClientMessages.HomePageMessages;

import Messages.Message;

public class AskSearchMessage implements Message {
    private final String searchedUsername;

    public AskSearchMessage(String searchedUsername) {
        this.searchedUsername = searchedUsername;
    }

    public String getSearchedUsername() {
        return searchedUsername;
    }
}
