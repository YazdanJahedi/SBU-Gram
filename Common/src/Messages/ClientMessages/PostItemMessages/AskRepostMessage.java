package Messages.ClientMessages.PostItemMessages;

import Messages.Message;
import Posts.Post;

public class AskRepostMessage implements Message {
    private final Post repostedPost;

    public AskRepostMessage(Post post) {
        this.repostedPost = post;
    }

    public Post getRepostedPost() {
        return repostedPost;
    }
}
