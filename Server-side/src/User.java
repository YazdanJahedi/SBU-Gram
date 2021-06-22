import Posts.Post;

import java.util.ArrayList;
import java.util.List;

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


    private final List<Post> allPosts = new ArrayList<>();
    private final List<Post> userPost = new ArrayList<>();

    // todo : better to use concurrent List instead of using synchronized word in this methods
    public synchronized void addPostToAllPosts(Post post){
        allPosts.add(post);
    }

    public synchronized void addPostToUserPosts(Post post){
        userPost.add(post);
    }

    public List<Post> getAllPosts() {
        return allPosts;
    }

    public List<Post> getUserPost() {
        return userPost;
    }
    

    public List<User> followers = new ArrayList<>();
    public List<User> followings = new ArrayList<>();

}
