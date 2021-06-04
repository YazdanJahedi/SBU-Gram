package Controller;

import Model.PageLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ResetPasswordPageController {

    @FXML
    public Button resetButton;
    public ImageView blackBackButton;
    public ImageView redBackButton;
    public Label beforeQuestionLabel;
    public TextField answerField;
    public Label beforePasswordLabel;
    public Label passwordLiable;
    public Label questionLabel;
    public Label wrongAnswerLabel;
    public Label wrongUsernameLabel;


    public void showRedBackButton(MouseEvent mouseEvent) {
        redBackButton.setVisible(true);
        blackBackButton.setVisible(false);
    }

    public void showBlackBackButton(MouseEvent mouseEvent) {
        redBackButton.setVisible(false);
        blackBackButton.setVisible(true);
    }

    public void goBack(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("LogInPage");
        } catch (IOException e) {
            System.err.println("~ Login Page is not found!");
        }
    }
}
