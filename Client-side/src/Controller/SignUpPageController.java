package Controller;

import Model.PageLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpPageController {
    public TextField usernameFiled;
    public ImageView showPasswordImage;
    public ImageView blackCloseButton;
    public ImageView redCloseButton;
    public Button nextButton;
    public TextField answerFiled;
    public MenuButton questionList;
    public Label wrongUsername;
    public Label wrongPassword;
    public Label wrongConfirm;
    public TextField shownPasswordField;
    public PasswordField hiddenPasswordField;
    public PasswordField confirmPasswordField;


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
        boolean everyThingIsOk = true;
        String username = usernameFiled.getText();
        String password;
        if (hiddenPasswordField.isVisible())
            password = hiddenPasswordField.getText();
        else
            password = shownPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String answer = answerFiled.getText();

        if (username.equals("") || password.equals("") || confirmPassword.equals("") || answer.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please complete all fields", ButtonType.CANCEL);
            alert.showAndWait();
            return;
        }

        wrongConfirm.setVisible(false);
        wrongUsername.setVisible(false);
        wrongPassword.setVisible(false);

        if (username.equals("ali")) {
            wrongUsername.setVisible(true);
            everyThingIsOk = false;
        }
        if (password.length() < 8) {
            wrongPassword.setVisible(true);
            everyThingIsOk = false;
        }
        if (!password.equals(confirmPassword)) {
            wrongConfirm.setVisible(true);
            everyThingIsOk = false;
        }

        if (everyThingIsOk) {
            try {
                new PageLoader().load("MakeProfilePage");
            } catch (IOException e) {
                System.err.println("~ page nto found!");
            }
        }
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

}
