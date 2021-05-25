package Controller;

import Model.PageLoader;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;

public class LoginPageController {

    @FXML
    public PasswordField hiddenPasswordField;
    public TextField shownPasswordField;
    public Button loginButton;
    public TextField usernameField;
    public Hyperlink signUpButton;
    public ImageView showPasswordImage;
    public ImageView SBU_logo;
    // -------- todo :
    public Label wrongPasswordLabel;
    public Hyperlink resetPasswordLink;
    public Label resetPasswordLabel;

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

    public void logIn(MouseEvent mouseEvent) {
    }
    public void logoMovement(MouseEvent mouseEvent) {
        RotateTransition logo = new RotateTransition(new Duration(700), SBU_logo);
        logo.setByAngle(360);
        logo.playFromStart();
    }

}
