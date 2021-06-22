package Controller;

import Messages.ClientMessages.HomePageMessages.AskPublishPostMessage;
import Messages.ClientMessages.HomePageMessages.AskSetProfileInformationMessage;
import Messages.ServerMessages.HomePageMessages.PublishPostMessage;
import Messages.ServerMessages.HomePageMessages.SetProfileInformationMessage;
import Model.Main;
import Model.PageLoader;

import Posts.Post;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomePageController {
    private final ObjectInputStream IN = Main.getObjectInputStream();
    private final ObjectOutputStream OUT = Main.getObjectOutputStream();

    // ------------------------------------------------------------------------------------------------
    // TIME LINE TAB :

    @FXML
    public Tab homeTab;

    public void goToHomeTab(Event event) {
    }


    // ------------------------------------------------------------------------------------------------
    // DIRECT MESSAGES TAB :


    @FXML
    public Tab directTab;

    public void goToDirectTab(Event event) {
    }


    // ------------------------------------------------------------------------------------------------
    // SEND POST TAB :

    private Post currentPost = new Post();

    @FXML
    public Tab sendPostTab;
    public TextField postTitleField;
    public ImageView postImage;
    public Button choosePostImageButton;
    public Button publishPostButton;
    public TextArea captionTextField;
    public Button clearButton;


    public void chooseImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your post's picture");

        // todo :
//        fileChooser.getExtensionFilters().addAll(//
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showOpenDialog(PageLoader.getPrimaryStage());

        if (file != null) {
            Image newProfile = new Image("file:" + file.getAbsolutePath());
            postImage.setImage(newProfile);
        }
    }

    public void clearPage(MouseEvent mouseEvent) {
        postTitleField.setText("");
        captionTextField.setText("");
        postImage.setImage(new Image("file:/Users/macbookpro/Desktop/University/CE Term2/AP/projects/SBU-Gram/Client-side/src/Images/blank-profile/blankPic.png"));
    }

    public void publishPost(MouseEvent mouseEvent) {
        currentPost.setTitle(postTitleField.getText());
        currentPost.setCaption(captionTextField.getText());
        currentPost.setPostImagePath(postImage.getImage().getUrl());

        try {
            OUT.writeObject(new AskPublishPostMessage(currentPost));
        } catch (IOException e) {
            System.err.println("~ ERROR: AskPublishPostMessage is not sent");
        }

        PublishPostMessage answer = null;
        try {
            answer = (PublishPostMessage)IN.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: AnkPublishPostMessage answer is not received");
        }

        Alert alert;

        assert answer != null;
        if(answer.isPostPublished()){
            alert = new Alert(Alert.AlertType.INFORMATION , "Your post is published!" , ButtonType.OK);
        } else{
            alert = new Alert(Alert.AlertType.ERROR , "Your post is not published because a problem!" , ButtonType.OK);
        }
        alert.showAndWait();


        currentPost = new Post();

        postTitleField.setText("");
        captionTextField.setText("");
        postImage.setImage(new Image("file:/Users/macbookpro/Desktop/University/CE Term2/AP/projects/SBU-Gram/Client-side/src/Images/blank-profile/blankPic.png"));
    }

    public void goToNewPostTab(Event event) {
    }


    // ------------------------------------------------------------------------------------------------
    // SEARCH TAB :


    @FXML
    public Tab searchTab;

    public void goToSearchTab(Event event) {
    }


    // ------------------------------------------------------------------------------------------------
    // MY PROFILE TAB :


    @FXML
    public Tab myProfileTab;
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
    public ListView<Post> userPostsList;


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

        //show the arraylist in listview
        userPostsList.setItems(FXCollections.observableArrayList(answer.getUserPosts()));
        userPostsList.setCellFactory(postList -> new PostItem());


    }
}
