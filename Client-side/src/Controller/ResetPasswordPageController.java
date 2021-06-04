package Controller;

import Messages.ClientMessages.MakeResetPasswordPageMessage;
import Messages.ClientMessages.SendResetAnswerMessage;
import Messages.ServerMessages.CheckResetAnswerMessage;
import Messages.ServerMessages.SendResetQuestionMessage;
import Model.Main;
import Model.PageLoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResetPasswordPageController {
    ObjectInputStream in = Main.getObjectInputStream();
    ObjectOutputStream out = Main.getObjectOutputStream();

    @FXML
    public Label enterUsernameLabel;
    public TextField usernameField;
    public Button enterButton;
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


    //

    public void enterUsername(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        out.writeObject(new MakeResetPasswordPageMessage(usernameField.getText()));
        SendResetQuestionMessage sendResetQuestionMessage = (SendResetQuestionMessage) in.readObject();
        if (sendResetQuestionMessage.isUserFound()) {
            usernameField.setVisible(false);
            enterButton.setVisible(false);
            enterUsernameLabel.setVisible(false);
            wrongUsernameLabel.setVisible(false);
            questionLabel.setText(sendResetQuestionMessage.getTheQuestion());
            questionLabel.setAlignment(Pos.CENTER);
            beforeQuestionLabel.setVisible(true);
            questionLabel.setVisible(true);
            answerField.setVisible(true);
            resetButton.setVisible(true);
        } else {
            wrongUsernameLabel.setVisible(true);
            beforeQuestionLabel.setVisible(false);
            questionLabel.setVisible(false);
            answerField.setVisible(false);
        }
    }


    public void resetPassword(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        out.writeObject(new SendResetAnswerMessage(answerField.getText()));
        CheckResetAnswerMessage checkResetAnswerMessage = (CheckResetAnswerMessage) in.readObject();

        if (checkResetAnswerMessage.isAnswerTrue()) {
            beforePasswordLabel.setVisible(true);
            passwordLiable.setText(checkResetAnswerMessage.getPassword());
            passwordLiable.setAlignment(Pos.CENTER);
            passwordLiable.setVisible(true);
            wrongAnswerLabel.setVisible(false);
        } else {
             wrongAnswerLabel.setVisible(true);
             beforePasswordLabel.setVisible(false);
             passwordLiable.setVisible(false);
        }
    }

}
