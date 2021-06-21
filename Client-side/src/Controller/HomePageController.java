package Controller;

import Messages.ClientMessages.HomePageMessages.AskSetProfileInformationMessage;
import Messages.ServerMessages.HomePageMessages.SetProfileInformationMessage;
import Model.Main;
import Model.PageLoader;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomePageController {
    private final ObjectInputStream IN = Main.getObjectInputStream();
    private final ObjectOutputStream OUT = Main.getObjectOutputStream();

    // ----------------------------------------------------


    @FXML
    public Tab homeTab;

    public void goToHomeTab(Event event) {
    }


    // ----------------------------------------------------


    @FXML
    public Tab directTab;

    public void goToDirectTab(Event event) {
    }


    // ----------------------------------------------------


    @FXML
    public Tab newPostTab;

    public void goToNewPostTab(Event event) {
    }


    // ----------------------------------------------------


    @FXML
    public Tab searchTab;

    public void goToSearchTab(Event event) {
    }


    // ----------------------------------------------------


    @FXML
    public Tab MyProfileTab;
    public ImageView profileImage;
    public Label usernameLabel;
    public Label nameLabel;
    public Label birthDateLabel;
    public Label bioLabel;
    public Label followersNumberLabel;
    public Label followingsNumberLabel;
    public Button logOutButton;
    public Button changeProfileButton;
    public Button deleteAccountButton;


    public void logOut(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("LoginPage");
        } catch (IOException e) {
            System.err.println("~ ERROR: loginPage is not found");
        }
    }

    public void changeProfile(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("MakeProfilePage");
        } catch (IOException e) {
            System.err.println("~ ERROR: MakeProfilePage is not found");
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) {
    }

    public void goToMyProfileTab(Event event) {
        try {
            OUT.writeObject(new AskSetProfileInformationMessage());
        } catch (IOException e) {
            System.err.println("~ ERROR: AskSetProfileInformationMessage is not sent");
        }

        SetProfileInformationMessage answer = null;
        try {
             answer = (SetProfileInformationMessage)IN.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: answer of AskSetProfileInformationMessage is not received");
        }

        assert answer != null;
        profileImage.setImage(new Image(answer.getProfileImagePath()));
        usernameLabel.setText(answer.getUsername());
        followersNumberLabel.setAlignment(Pos.CENTER);
        followingsNumberLabel.setAlignment(Pos.CENTER);
        followersNumberLabel.setText(answer.getFollowersNumber());
        followingsNumberLabel.setText(answer.getFollowingsNumber());
        nameLabel.setText(answer.getFirstName() + " " + answer.getLastName());
        birthDateLabel.setText(answer.getBirthDate());
        bioLabel.setText(answer.getBio());

    }
}
