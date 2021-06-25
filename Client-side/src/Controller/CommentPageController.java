package Controller;

import Model.PageLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CommentPageController {

    @FXML
    public ListView<String> commentsList;
    public Label postTitleLabel;
    public TextArea commentTextField;
    public Button sendButton;
    public Button clearButton;
    public ImageView blackBackButton;
    public ImageView redBackButton;

    public void initialize(){
        //
    }

    public void sendComment(MouseEvent mouseEvent) {
        //
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
