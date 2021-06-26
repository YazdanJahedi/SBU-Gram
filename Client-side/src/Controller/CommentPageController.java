package Controller;

import Messages.ClientMessages.PostItemMessages.AskAddCommentMessage;
import Messages.ClientMessages.PostItemMessages.AskSetCommentPageMessage;
import Messages.ServerMessages.PostItemMessages.AddCommentMessage;
import Model.Main;
import Model.PageLoader;
import Posts.Post;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
            AskSetCommentPageMessage answer = null;
            try {
                answer = (AskSetCommentPageMessage) IN.readObject();
                System.out.println("answer is received");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("~ ERROR: answer of AskSetCommentPageMessage is not received");
            }

            assert answer != null;
            post = answer.getPost();
        }

        postTitleLabel.setText(post.getTitle());

        commentsList.setItems(FXCollections.observableArrayList(post.getComments()));
    }

    public void sendComment(MouseEvent mouseEvent) {
        if (commentTextField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Comment field is empty"  , ButtonType.CLOSE);
            alert.showAndWait();
            return;
        }

        try {
            OUT.writeObject(new AskAddCommentMessage(post , commentTextField.getText()));
        } catch (IOException e) {
            System.err.println("~ ERROR: AskAddCommentMessage is not sent");
        }

        try {
            AddCommentMessage answer = (AddCommentMessage) IN.readObject();
            post = answer.getPost();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: answer of AskAddCommentMessage is not received");
        }

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
