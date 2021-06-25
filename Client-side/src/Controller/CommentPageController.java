package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CommentPageController {

    @FXML
    public ListView<String> commentsList;
    public Label postTitleLabel;
    public TextArea commentTextField;
    public Button sendButton;
    public Button clearButton;
    public ImageView blackBackButton;
    public ImageView redBackButton;

    public void sendComment(MouseEvent mouseEvent) {
    }

    public void clear(MouseEvent mouseEvent) {
    }

    public void showRedBackButton(MouseEvent mouseEvent) {
    }

    public void showBlackBackButton(MouseEvent mouseEvent) {
    }

    public void goBack(MouseEvent mouseEvent) {
    }
}
