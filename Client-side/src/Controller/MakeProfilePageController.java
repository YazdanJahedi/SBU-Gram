package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import Model.PageLoader;

public class MakeProfilePageController {

    @FXML
    public Button addProfileImageButton;
    public ImageView profileImage;
    public Button doneButton;
    public TextField firstNameField;
    public TextField lastNameFiled;
    public TextField bioField;
    public DatePicker birthDate;
    public ImageView blackBackButton;

    public void addProfileImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your profile picture");

//        fileChooser.getExtensionFilters().addAll(//
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showOpenDialog(PageLoader.getPrimaryStage());

        if(file != null){
            Image newProfile = new Image("file:"+file.getAbsolutePath());
            profileImage.setImage(newProfile);
        }
    }

    public void goBack(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("SignUpPage");
        } catch (IOException e) {
            System.err.println("~ page not found!");
        }
    }

    public void createAccount(MouseEvent mouseEvent) {
    }

}
