package Controller;

import Posts.Post;
import javafx.scene.control.ListCell;


public class PostItem extends ListCell<Post> {

    @Override
    public void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);
        System.out.println("postItem 1");
        if (post != null) {
            setGraphic(new PostItemController(post).init());
            System.out.println("postItem 2");
        }
    }
}
