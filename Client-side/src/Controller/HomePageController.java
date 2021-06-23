package Controller;

import Messages.ClientMessages.HomePageMessages.AskPublishPostMessage;
import Messages.ClientMessages.HomePageMessages.AskSearchMessage;
import Messages.ClientMessages.HomePageMessages.AskSetProfileInformationMessage;
import Messages.ServerMessages.HomePageMessages.PublishPostMessage;
import Messages.ServerMessages.HomePageMessages.SearchMessage;
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
            answer = (PublishPostMessage) IN.readObject();
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
        try {
            OUT.writeObject(new AskSearchMessage(searchField.getText()));
        } catch (IOException e) {
            System.err.println("~ ERROR: AskSearchMessage is not sent");
        }

        SearchMessage answer = null;
        try {
            answer = (SearchMessage) IN.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("~ ERROR: answer of AskSearchMessage is not received");
        }

        assert answer != null;
        if(!answer.isUserFound()){
            searchNotFoundLabel.setVisible(true);
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
        } else{
            searchNotFoundLabel.setVisible(false);
            searchFollowersLabel.setVisible(true);
            searchFollowingsLabel.setVisible(true);
            searchField.setText("");
            searchBlockButton.setVisible(true);
            searchMuteButton.setVisible(true);
            searchPostsList.setVisible(true);

            //todo : base on the user it should be set
            searchFollowButton.setVisible(true);
            searchUnfollowButton.setVisible(false);

            searchUsernameLabel.setVisible(true);
            searchUsernameLabel.setText(answer.getUsername());

            searchProfileImage.setVisible(true);
            searchProfileImage.setImage(new Image(answer.getProfileImagePath()));

            searchNameLabel.setVisible(true);
            searchNameLabel.setText(answer.getFirstName() + " " + answer.getLastName());

            searchBioLabel.setVisible(true);
            searchBioLabel.setText(answer.getBio());

            searchBirthDateLabel.setVisible(true);
            searchBirthDateLabel.setText(answer.getBirthDate());

            searchFollowersNumberLabel.setVisible(true);
            searchFollowersNumberLabel.setText(answer.getFollowersNumber());

            searchFollowingsNumberLabel.setVisible(true);
            searchFollowingsNumberLabel.setText(answer.getFollowingsNumber());

            //show the arraylist in listview
            searchPostsList.setItems(FXCollections.observableArrayList(answer.getUserPosts()));
            searchPostsList.setCellFactory(searchPostsList -> new PostItem());
        }
    }

    public void follow(MouseEvent mouseEvent) {
        searchFollowButton.setVisible(false);
        searchUnfollowButton.setVisible(true);
        // todo ...
    }

    public void unfollow(MouseEvent mouseEvent) {
        searchUnfollowButton.setVisible(false);
        searchFollowButton.setVisible(true);
        // todo ...
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


        //show the arraylist in listview
        userPostsList.setItems(FXCollections.observableArrayList(answer.getUserPosts()));
        userPostsList.setCellFactory(userPostsList -> new PostItem());

    }

}
