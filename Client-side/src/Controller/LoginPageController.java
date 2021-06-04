package Controller;

import Messages.ClientMessages.LogInMessage;
import Messages.ServerMessages.FindUserMessage;
import Model.Main;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.PageLoader;

public class LoginPageController {
    ObjectInputStream in = Main.getObjectInputStream();
    ObjectOutputStream out = Main.getObjectOutputStream();

    @FXML
    public PasswordField hiddenPasswordField;
    public TextField shownPasswordField;
    public Button loginButton;
    public TextField usernameField;
    public Hyperlink signUpButton;
    public ImageView showPasswordImage;
    public ImageView SBU_logo;
    public Label wrongPasswordLabel;
    public Hyperlink resetPasswordLink;
    public Label resetPasswordLabel;

    public void logoMovement(MouseEvent mouseEvent) {
        RotateTransition logo = new RotateTransition(new Duration(700), SBU_logo);
        logo.setByAngle(360);
        logo.playFromStart();
    }

    public void showPassword(MouseEvent mouseEvent) {
        if (!shownPasswordField.isVisible()) {
            shownPasswordField.setVisible(true);
            hiddenPasswordField.setVisible(false);
            shownPasswordField.setText(hiddenPasswordField.getText());
        } else {
            hiddenPasswordField.setVisible(true);
            shownPasswordField.setVisible(false);
            hiddenPasswordField.setText(shownPasswordField.getText());
        }
    }

    public void signUp(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("SignUpPage");
        } catch (IOException e) {
            System.err.println("~ page not found!");
        }
    }

    public void resetPassword(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("ResetPasswordPage");
        } catch (IOException e) {
            System.err.println("~ resetPassword page is not found!");
        }
    }


    //


    public void logIn(MouseEvent mouseEvent) {
        String username = usernameField.getText();
        String password;
        if (shownPasswordField.isVisible())
            password = shownPasswordField.getText();
        else
            password = hiddenPasswordField.getText();

        try {
            out.writeObject(new LogInMessage(username , password));
            System.out.println("logIn message is sent!");
        } catch (IOException e) {
            System.err.println("~ log in page -> lonIn method -> send longInMessage : ERROR");
        }

        FindUserMessage findUserMessage = null;
        try {
            findUserMessage = (FindUserMessage) in.readObject();
            System.out.println("Is user found?  :" + findUserMessage.isUserFound());
            System.out.println("----------------");
        } catch (Exception e) {
            System.err.println("~ log in page -> lonIn method -> read FindUserMessage : ERROR");
        }

//        assert findUserMessage != null;
        assert findUserMessage != null;
        if (findUserMessage.isUserFound()) {
            wrongPasswordLabel.setVisible(false);
            resetPasswordLabel.setVisible(false);
            resetPasswordLink.setVisible(false);
            // todo : load new page
        } else {
            wrongPasswordLabel.setVisible(true);
            resetPasswordLabel.setVisible(true);
            resetPasswordLink.setVisible(true);
        }
    }

}
