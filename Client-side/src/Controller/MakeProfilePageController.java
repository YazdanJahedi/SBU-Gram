package Controller;

import Model.Main;
import Model.PageLoader;

import Messages.ClientMessages.ChangeProfileMessage;
import Messages.ServerMessages.IsProfileChangedMessage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MakeProfilePageController {
    private final ObjectInputStream IN = Main.getObjectInputStream();
    private final ObjectOutputStream OUT = Main.getObjectOutputStream();

    @FXML
    public Button addProfileImageButton;
    public ImageView profileImage;
    public Button doneButton;
    public TextField firstNameField;
    public TextField lastNameFiled;
    public TextField bioField;
    public DatePicker birthDateField;


    public void chooseProfileImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your profile picture");

        // todo :
//        fileChooser.getExtensionFilters().addAll(//
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showOpenDialog(PageLoader.getPrimaryStage());

        if (file != null) {
            Image newProfile = new Image("file:" + file.getAbsolutePath());
            profileImage.setImage(newProfile);
        }
    }

    public void changeProfile(MouseEvent mouseEvent) {
        String firstName = firstNameField.getText();
        String lastName = lastNameFiled.getText();
        String bio = bioField.getText();
        String birthDate = "";
        if (birthDateField.getValue() != null)
            birthDate = birthDateField.getValue().toString().replaceAll("-" , "/");
        String profileImagePath = profileImage.getImage().getUrl();

        try {
            // todo :
            OUT.writeObject(new ChangeProfileMessage(firstName, lastName, bio, birthDate, profileImagePath));
        } catch (IOException e) {
            System.err.println("~ ERROR: ChangeProfileMessage is not sent");
        }

        IsProfileChangedMessage isProfileChangedMessage = null;
        try {
            isProfileChangedMessage = (IsProfileChangedMessage) IN.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: answer of ChangeProfileMessage is not received");
        }


        assert isProfileChangedMessage != null;
        if (isProfileChangedMessage.isProfileChanged()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION , "Your profile information is changed successfully!" , ButtonType.OK);
            alert.showAndWait();

            try {
                new PageLoader().load("HomePage");
            } catch (IOException e) {
                System.err.println("~ ERROR: HomePage is not found!");
            }
        } else {
            System.err.println("~ your profile is not changed");
        }
    }

}
