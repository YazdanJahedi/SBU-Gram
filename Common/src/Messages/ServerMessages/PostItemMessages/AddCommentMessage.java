package Messages.ServerMessages.PostItemMessages;

import Messages.Message;
import Posts.Post;

public class AddCommentMessage implements Message {
    private final Post post;

    public AddCommentMessage(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
