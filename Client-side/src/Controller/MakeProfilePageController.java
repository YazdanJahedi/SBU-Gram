package Controller;

import Messages.ClientMessages.ChangeProfileMessage;
import Messages.ServerMessages.IsProfileChangedMessage;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.PageLoader;

public class MakeProfilePageController {
    ObjectInputStream in = Main.getObjectInputStream();
    ObjectOutputStream out = Main.getObjectOutputStream();

    @FXML
    public Button addProfileImageButton;
    public ImageView profileImage;
    public Button doneButton;
    public TextField firstNameField;
    public TextField lastNameFiled;
    public TextField bioField;
    public DatePicker birthDateField;
    public ImageView blackBackButton;

    public void addProfileImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your profile picture");

//        fileChooser.getExtensionFilters().addAll(//
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showOpenDialog(PageLoader.getPrimaryStage());

        if (file != null) {
            Image newProfile = new Image("file:" + file.getAbsolutePath());
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

    public void changeProfile(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        String firstName = firstNameField.getText();
        System.out.println(firstName);
        String lastName = lastNameFiled.getText();
        System.out.println(lastName);
        String bio = bioField.getText();
        System.out.println(bio);
        String birthDate = "";
        if (birthDateField.getValue() != null)
            birthDate = birthDateField.getValue().toString();
        System.out.println(birthDate);
//        Image profImage = profileImage.getImage();

        out.writeObject(new ChangeProfileMessage(firstName, lastName, bio, birthDate, new String("123")/*profImage*/));
        IsProfileChangedMessage isProfileChangedMessage = (IsProfileChangedMessage) in.readObject();


        System.out.println("now we are here!!!!");


        assert isProfileChangedMessage != null;
        if (isProfileChangedMessage.isProfileChanged()) {

            System.out.println(" your profile is changed");
            // todo :  better : load directly the time line page ...
            try {
                new PageLoader().load("LoginPage");
            } catch (IOException e) {
                System.err.println("~ LoginPage not found!");
            }
        } else {
            System.err.println(" ! profile is not changed ...");
        }
    }

}
