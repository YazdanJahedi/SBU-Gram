package Controller;

import Messages.ClientMessages.PostItemMessages.AskRepostMessage;
import Messages.ServerMessages.PostItemMessages.SetRepostMessage;
import Model.Main;
import Model.PageLoader;
import Posts.Post;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PostItemController {
    private final ObjectInputStream IN = Main.getObjectInputStream();
    private final ObjectOutputStream OUT = Main.getObjectOutputStream();

    private Post post;

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
        System.out.println("new postItemController is working...");
        try {
            new PageLoader().load("PostItem", this);
        } catch (IOException e) {
            System.err.println("~ ERROR: PostItem is not found");
            return;
        }
        this.post = post;
    }


    public AnchorPane init() {
        titleLabel.setText(post.getTitle());
        captionTextField.setText(post.getCaption());
        usernameLabel.setText(post.getUsername());
        writerNameLabel.setText(post.getWriter());
        dateAndTimeLabel.setText(post.getDateAndTime());
        postImage.setImage(new Image(post.getPostImagePath()));
        profileImage.setImage(new Image(post.getProfileImagePath()));

        likesCounterLabel.setText(post.getLikesCounter().toString());
        repostsCounterLabel.setText(post.getRepostsCounter().toString());
        likesCounterLabel.setAlignment(Pos.CENTER_RIGHT);
        repostsCounterLabel.setAlignment(Pos.CENTER_RIGHT);

        //set another image dynamically
//        if (post.getWriter().equals("ali alavi"))
//            profileImage.setImage(new Image(Paths.get("images/ali_alavi.jpg").toUri().toString()));

        return root;
    }


    public void like(MouseEvent mouseEvent) {
        likeButton.setVisible(false);
        disLikeButton.setVisible(true);

    }

    public void disLike(MouseEvent mouseEvent) {
        disLikeButton.setVisible(false);
        likeButton.setVisible(true);
    }

    public void comment(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("CommentPage");
        } catch (IOException e) {
            System.err.println("~ ERROR: CommentPage is not found");
        }
    }

    public void repost(MouseEvent mouseEvent) {
        try {
            OUT.writeObject(new AskRepostMessage(post));
        } catch (IOException e) {
            System.err.println("~ ERROR: AskRepostMessage is not sent");
        }

        SetRepostMessage answer = null;
        try {
            answer = (SetRepostMessage) IN.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: answer of AskRepostMessage is not received");
        }

        assert answer != null;
        if(answer.isReposted()){
            RotateTransition logo = new RotateTransition(new Duration(500), repostButton);
            logo.setByAngle(360);
            logo.playFromStart();
        }

        synchronized (post){
            post.repost();
        }
    }

    public void showUserProfile(MouseEvent mouseEvent) {
    }
}
