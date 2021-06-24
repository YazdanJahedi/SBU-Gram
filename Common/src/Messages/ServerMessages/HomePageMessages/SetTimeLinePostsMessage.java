package Messages.ServerMessages.HomePageMessages;

import Messages.Message;
import Posts.Post;

import java.util.ArrayList;

public class SetTimeLinePostsMessage implements Message {
    private final ArrayList<Post> allPosts ;

    public SetTimeLinePostsMessage(ArrayList<Post> allPosts) {
        this.allPosts = allPosts;
    }

    public ArrayList<Post> getAllPosts() {
        return allPosts;
    }
}
