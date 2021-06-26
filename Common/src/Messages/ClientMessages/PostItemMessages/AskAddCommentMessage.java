package Messages.ClientMessages.PostItemMessages;

import Messages.Message;
import Posts.Post;

public class AskAddCommentMessage implements Message {
    private final String comment;
    private final Post post;

    public AskAddCommentMessage(Post post , String comment) {
        this.post = post;
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public String getComment() {
        return comment;
    }
}
