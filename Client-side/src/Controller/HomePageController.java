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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
    public TextArea captionTextField;
    public Button choosePostImageButton;
    public Button publishPostButton;
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
        System.out.println("publish post button is clicked");
        currentPost.setTitle(postTitleField.getText());
        currentPost.setCaption(captionTextField.getText());
        currentPost.setPostImagePath(postImage.getImage().getUrl());

        try {
            OUT.writeObject(new AskPublishPostMessage(currentPost));
            System.out.println("ask publish post is sent");
        } catch (IOException e) {
            System.err.println("~ ERROR: AskPublishPostMessage is not sent");
        }

        PublishPostMessage answer = null;
        try {
            answer = (PublishPostMessage) IN.readObject();
            System.out.println("answer is received");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: AnkPublishPostMessage answer is not received");
        }

        Alert alert;

        assert answer != null;
        if (answer.isPostPublished()) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Your post is published!", ButtonType.OK);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Your post is not published because a problem!", ButtonType.OK);
        }
        alert.showAndWait();


        currentPost = new Post();

        postTitleField.setText("");
        captionTextField.setText("");
        // todo : make this better :
        postImage.setImage(new Image("file:/Users/macbookpro/Desktop/University/CE Term2/AP/projects/SBU-Gram/Client-side/src/Images/blank-profile/blankPic.png"));

        System.out.println("publish post method is done\n\n");
    }

    public void goToNewPostTab(Event event) {
    }


    // ------------------------------------------------------------------------------------------------
    // SEARCH TAB :


    @FXML
    public Tab searchTab;
    public ImageView searchProfileImage;
    public Label searchUsernameLabel;
    public Label searchFollowersLabel;
    public TextField searchField;
    public Button searchButton;
    public Label searchFollowingsLabel;
    public Label searchFollowersNumberLabel;
    public Label searchFollowingsNumberLabel;
    public Label searchNameLabel;
    public Label searchBirthDateLabel;
    public Label searchBioLabel;
    public ListView<Post> searchPostsList;
    public Button searchUnfollowButton;
    public Button searchFollowButton;
    public Button searchBlockButton;
    public Button searchMuteButton;
    public Label searchNotFoundLabel;


    public void search(MouseEvent mouseEvent) {
    }

    public void follow(MouseEvent mouseEvent) {
    }

    public void unfollow(MouseEvent mouseEvent) {
    }

    public void block(MouseEvent mouseEvent) {
    }

    public void mute(MouseEvent mouseEvent) {
    }

    public void goToSearchTab(Event event) {
        searchNotFoundLabel.setVisible(false);
        searchField.setText("");
        searchUsernameLabel.setVisible(false);
        searchProfileImage.setVisible(false);
        searchNameLabel.setVisible(false);
        searchBioLabel.setVisible(false);
        searchBirthDateLabel.setVisible(false);
        searchFollowersLabel.setVisible(false);
        searchFollowingsLabel.setVisible(false);
        searchFollowersNumberLabel.setVisible(false);
        searchFollowingsNumberLabel.setVisible(false);
        searchBlockButton.setVisible(false);
        searchFollowButton.setVisible(false);
        searchUnfollowButton.setVisible(false);
        searchMuteButton.setVisible(false);
        searchPostsList.setVisible(false);
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
        System.out.println("now we are in MyProfile tab!");
        try {
            OUT.writeObject(new AskSetProfileInformationMessage());
        } catch (IOException e) {
            System.err.println("~ ERROR: AskSetProfileInformationMessage is not sent");
        }

        SetProfileInformationMessage answer = null;
        try {
            answer = (SetProfileInformationMessage) IN.readObject();
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

        System.out.println("\n!!!!!!!!!!");
        System.out.println("answer: UserPostList size:");
        System.out.println(answer.getUserPosts().size() + "\n");
        System.out.println("all Posts:");
        for (Post p: answer.getUserPosts()) {
            System.out.println(p.toString());
        }
        System.out.println("!!!!!!!!\n");



        //show the arraylist in listview
//        userPostsList.setItems(FXCollections.observableArrayList(answer.getUserPosts()));
//        System.out.println("1");
//        userPostsList.setCellFactory(userPostsList -> new PostItem());
//        System.out.println("2");

    }

}
