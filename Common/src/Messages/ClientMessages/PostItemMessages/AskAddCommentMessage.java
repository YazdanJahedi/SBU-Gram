package Messages.ClientMessages.PostItemMessages;

import Messages.Message;
import Posts.Post;

public class AskAddCommentMessage implements Message {
    private final Post post;

    public AskAddCommentMessage(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
