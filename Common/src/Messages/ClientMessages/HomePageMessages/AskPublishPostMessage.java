package Messages.ClientMessages.HomePageMessages;


import Messages.Message;
import Posts.Post;

public class AskPublishPostMessage implements Message {
    private final Post post;

    public AskPublishPostMessage(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
