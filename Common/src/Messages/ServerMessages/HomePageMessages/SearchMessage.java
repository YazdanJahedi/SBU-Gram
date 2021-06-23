package Messages.ServerMessages.HomePageMessages;

import Messages.Message;
import Posts.Post;

import java.util.ArrayList;

public class SearchMessage implements Message {
    private final boolean isUserFound;

    public SearchMessage(boolean isUserFound) {
        this.isUserFound = isUserFound;
    }

    public boolean isUserFound() {
        return isUserFound;
    }

    private String username;
    private String profileImagePath;
    private String firstName;
    private String lastName;
    private String bio;
    private String birthDate;
    private String followersNumber;
    private String followingsNumber;
    private ArrayList<Post> userPosts;

    public SearchMessage(boolean isUserFound, String username, String profileImagePath,
                         String firstName, String lastName, String bio, String birthDate ,
                         String followersNumber , String followingNumber ,
                        ArrayList<Post> userPosts) {
        this.isUserFound = isUserFound;
        this.username = username;
        this.profileImagePath = profileImagePath;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birthDate = birthDate;
        this.followersNumber = followersNumber;
        this.followingsNumber = followingNumber;
        this.userPosts = userPosts;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFollowersNumber() {
        return followersNumber;
    }

    public String getFollowingsNumber() {
        return followingsNumber;
    }

    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }
}
