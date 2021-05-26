package Controller;

import Model.PageLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SignUpPageController {
    public TextField usernameFiled;
    public PasswordField passwordFiled1;
    public PasswordField passwordField2;
    public ImageView showPasswordImage;
    public ImageView blackCloseButton;
    public ImageView redCloseButton;



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

    public void nextPage(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("MakeProfilePage");
        } catch (IOException e) {
            System.err.println("~ page nto found!");
        }
    }
}
