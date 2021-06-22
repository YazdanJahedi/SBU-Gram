package Messages.ServerMessages.HomePageMessages;

import Messages.Message;
import Posts.Post;

import java.util.ArrayList;
import java.util.List;

public class SetProfileInformationMessage implements Message {
    private final String USER_NAME;
    private final String PROFILE_IMAGE_PATH;
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String BIO;
    private final String BIRTH_DATE;
    private final String FOLLOWERS_NUMBER;
    private final String FOLLOWINGS_NUMBER;

    public SetProfileInformationMessage(String username, String profileImagePath, String firstName,
                                        String lastName, String bio, String birthDate,
                                        String followersNum, String followingsNum) {
        USER_NAME = username;
        PROFILE_IMAGE_PATH = profileImagePath;
        FIRST_NAME = firstName;
        LAST_NAME =lastName;
        BIO = bio;
        BIRTH_DATE = birthDate;
        FOLLOWERS_NUMBER = followersNum;
        FOLLOWINGS_NUMBER = followingsNum;
    }

    public String getFirstName() {
        return FIRST_NAME;
    }

    public String getLastName() {
        return LAST_NAME;
    }

    public String getBio() {
        return BIO;
    }

    public String getBirthDate() {
        return BIRTH_DATE;
    }

    public String getUsername() {
        return USER_NAME;
    }

    public String getProfileImagePath() {
        return PROFILE_IMAGE_PATH;
    }

    public String getFollowersNumber() {
        return FOLLOWERS_NUMBER;
    }

    public String getFollowingsNumber() {
        return FOLLOWINGS_NUMBER;
    }


    private List<Post> userPosts = new ArrayList<>();

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }


}
