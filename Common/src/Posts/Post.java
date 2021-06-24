package Posts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
    private String profileImagePath;
    private String postImagePath;
    private String title;
    private String writer;
    private String username;
    private String caption;
    private String dateAndTime;

    private Integer likesCounter = 0;
    private Integer repostsCounter = 0;

    private List<String> comments = new ArrayList<>();


    public Post() {
    }

    public Post(String profileImagePath, String postImagePath, String title, String writer,
                String username, String caption, String dateAndTime, Integer likesCounter,
                Integer repostsCounter) {
        this.profileImagePath = profileImagePath;
        this.postImagePath = postImagePath;
        this.title = title;
        this.writer = writer;
        this.username = username;
        this.caption = caption;
        this.dateAndTime = dateAndTime;
        this.likesCounter = likesCounter;
        this.repostsCounter = repostsCounter;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public void setPostImagePath(String postImagePath) {
        this.postImagePath = postImagePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
    }

    public void setRepostsCounter(Integer repostsCounter) {
        this.repostsCounter = repostsCounter;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public String getPostImagePath() {
        return postImagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getUsername() {
        return username;
    }

    public String getCaption() {
        return caption;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public Integer getLikesCounter() {
        return likesCounter;
    }

    public Integer getRepostsCounter() {
        return repostsCounter;
    }

    public void like(){
        likesCounter++;
    }

    public void repost(){
        repostsCounter++;
    }

    public List<String> getComments() {
        return comments;
    }


    // todo : this is not very good . change it ...

    @Override
    public String toString() {
        return "Post{" +
                "profileImagePath='" + profileImagePath + '\'' +
                ", postImagePath='" + postImagePath + '\'' +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", username='" + username + '\'' +
                ", caption='" + caption + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}
