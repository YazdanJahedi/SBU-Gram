package Controller;

import Model.PageLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpPageController {
    public TextField firstNameField;
    public TextField lastNameFiled;
    public DatePicker birthDayField;
    public TextField usernameFiled;
    public PasswordField passwordFiled1;
    public PasswordField passwordField2;
    public Button createAccountButton;
    public ImageView showPasswordImage;
    public ImageView blackCloseButton;
    public ImageView redCloseButton;
    public TextField answerField;

    public void showRedCloseButton(MouseEvent mouseEvent) {
        redCloseButton.setVisible(true);
    }

    public void showBlackCloseButton(MouseEvent mouseEvent) {
        redCloseButton.setVisible(false);
    }

    public void closePage(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("LoginPage");
        } catch (IOException e) {
            System.err.println("~ page not found");
        }
    }


}
