import Posts.Post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private final String username;
    private final String password;
    private final String theQuestion;
    private final String answerOfTheQuestion;

    public User(String username, String password, String theQuestion, String answerOfTheQuestion) {
        this.username = username;
        this.password = password;
        this.theQuestion = theQuestion;
        this.answerOfTheQuestion = answerOfTheQuestion;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public String getAnswerOfTheQuestion() {
        return answerOfTheQuestion;
    }


    //-------------------------------------------------------------------------------------------


    private String profileImage = "";
    private String firstName = "";
    private String lastName = "";
    private String bio = "";
    private String birthDate = "";

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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

    public String getProfileImage() {
        return profileImage;
    }


    //-------------------------------------------------------------------------------------------


    //    private final ArrayList<Post> allPosts = new ArrayList<>();
    private final ArrayList<Post> userPosts = new ArrayList<>();

//    public ArrayList<Post> getAllPosts() {
//        return allPosts;
//    }

    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }


    //-------------------------------------------------------------------------------------------


    private final Set<User> followers = new HashSet<>();
    private final Set<User> followings = new HashSet<>();

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowings() {
        return followings;
    }

//-------------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts number=" + userPosts.size() +
                ", followers number=" + followers.size() +
                ", followings number=" + followings.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
