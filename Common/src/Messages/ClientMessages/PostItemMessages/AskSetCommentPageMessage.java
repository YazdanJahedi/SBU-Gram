package Messages.ClientMessages.PostItemMessages;

import Messages.Message;
import Posts.Post;

public class AskSetCommentPageMessage implements Message {
    private final Post post;

    public AskSetCommentPageMessage(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
