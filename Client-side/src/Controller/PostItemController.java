package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PostItemController {

    @FXML
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
