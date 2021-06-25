package Controller;

import Messages.ClientMessages.PostItemMessages.AskAddCommentMessage;
import Model.Main;
import Model.PageLoader;
import Posts.Post;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommentPageController {
    private final ObjectInputStream IN = Main.getObjectInputStream();
    private final ObjectOutputStream OUT = Main.getObjectOutputStream();

    private Post post = null;

    @FXML
    public ListView<String> commentsList;
    public Label postTitleLabel;
    public TextArea commentTextField;
    public Button sendButton;
    public Button clearButton;
    public ImageView blackBackButton;
    public ImageView redBackButton;

    public void initialize(){
        if (post == null) {
            AskAddCommentMessage answer = null;
            try {
                answer = (AskAddCommentMessage) IN.readObject();
                System.out.println("answer is received");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("~ ERROR: answer of AskAddCommentMessage is not received");
            }

            assert answer != null;
            post = answer.getPost();
        }

        postTitleLabel.setText(post.getTitle());

        commentsList.setItems(FXCollections.observableArrayList(post.getComments()));
    }

    public void sendComment(MouseEvent mouseEvent) {
        if (commentTextField.getText().equals(""))
            return;

        post.getComments().add(commentTextField.getText());

        initialize();

    }

    public void clear(MouseEvent mouseEvent) {
        commentTextField.setText("");
    }

    public void showRedBackButton(MouseEvent mouseEvent) {
        blackBackButton.setVisible(false);
        redBackButton.setVisible(true);
    }

    public void showBlackBackButton(MouseEvent mouseEvent) {
        blackBackButton.setVisible(true);
        redBackButton.setVisible(false);
    }

    public void goBack(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("HomePage");
        } catch (IOException e) {
            System.err.println("~ ERROR: Homepage is not found");
        }
    }
}
