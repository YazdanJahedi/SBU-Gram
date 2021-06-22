package Controller;

import Model.PageLoader;
import Posts.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    private Post post ;

    @FXML
    public AnchorPane root;
    public Label titleLabel;
    public TextArea captionTextField;
    public Hyperlink usernameLabel;
    public Label writerNameLabel;
    public Label likesCounterLabel;
    public Label repostsCounterLabel;
    public Label dateAndTimeLabel;
    public ImageView postImage;
    public ImageView profileImage;
    public ImageView repostButton;
    public ImageView commentButton;
    public ImageView likeButton;
    public ImageView disLikeButton;

    public PostItemController(Post post) {
        try {
            new PageLoader().load("PostItem", this);
        } catch (IOException e) {
            System.err.println("~ ERROR: PostItem is not found");
            return;
        }
        this.post = post;
    }

    public AnchorPane init() {
        usernameLabel.setText(post.getWriter());
        titleLabel.setText(post.getTitle());

        //set another image dynamically
        if (post.getWriter().equals("ali alavi"))
            profileImage.setImage(new Image(Paths.get("images/ali_alavi.jpg").toUri().toString()));
        return root;
    }

    public void like(MouseEvent mouseEvent) {
    }

    public void disLike(MouseEvent mouseEvent) {
    }

    public void comment(MouseEvent mouseEvent) {
    }

    public void repost(MouseEvent mouseEvent) {
    }

    public void showUserProfile(MouseEvent mouseEvent) {
    }
}
