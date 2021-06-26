import Messages.ClientMessages.HomePageMessages.AskFollowMessage;
import Messages.ClientMessages.HomePageMessages.AskPublishPostMessage;
import Messages.ClientMessages.HomePageMessages.AskSearchMessage;
import Messages.ClientMessages.HomePageMessages.AskUnfollowMessage;
import Messages.ClientMessages.PostItemMessages.AskRepostMessage;
import Messages.Message;
import Messages.ClientMessages.*;
import Messages.ServerMessages.*;
import Messages.ServerMessages.HomePageMessages.*;
import Messages.ServerMessages.PostItemMessages.SetRepostMessage;
import Posts.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MessageHandler {
    private final static DataBase dataBase = DataBase.getInstance();
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public static synchronized void closeMessage(String username) {
        System.out.println("* " + username + " closed the program");
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
    }

    public static synchronized Message loginHandler(LogInMessage logInMessage) {
        System.out.println(logInMessage.getUsername() + " wants to login ...");

        if (dataBase.getData().containsKey(logInMessage.getUsername())) {
            String password = dataBase.getData().get(logInMessage.getUsername()).getPassword();

            if (password.equals(logInMessage.getPassword())) {
                System.out.println(logInMessage.getUsername() + " is logged in");
                System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
                return new FindUserMessage(true);
            }
        }

        System.out.println(logInMessage.getUsername() + " 's username or password is incorrect");
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        return new FindUserMessage(false);
    }

    public static synchronized Message SignupHandler(SignUpMessage signUpMessage) {
        System.out.println(signUpMessage.getUsername() + " wants to Sign up...");
        CreateAccountMessage answer = new CreateAccountMessage();

        if (dataBase.getData().containsKey(signUpMessage.getUsername())) {
            System.out.println("username already exists");
            answer.setUsernameValid(false);
        }
        if (signUpMessage.getPassword().length() < 8) {
            System.out.println("password is invalid");
            answer.setPasswordValid(false);
        }
        if (!signUpMessage.getPassword().equals(signUpMessage.getConfirmPassword())) {
            System.out.println("confirm password is incorrect");
            answer.setConfirmPasswordValid(false);
        }

        if (answer.isPasswordValid() && answer.isUsernameValid() && answer.isConfirmPasswordValid()) {
            dataBase.getData().
                    put(signUpMessage.getUsername(),
                            new User(signUpMessage.getUsername(),
                                    signUpMessage.getPassword(), signUpMessage.getTheQuestion()
                                    , signUpMessage.getAnswerOfTheQuestion()));

            System.out.println(signUpMessage.getUsername() + " 's account is created!");
        }
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
        return answer;
    }

    public static synchronized Message makeResetPasswordPage(MakeResetPasswordPageMessage makeResetPasswordPageMessage, String username) {
        if (dataBase.getData().containsKey(makeResetPasswordPageMessage.getUsername()))
            return new SendResetQuestionMessage(true, dataBase.getData().get(username).getTheQuestion());

        return new SendResetQuestionMessage(false, "");
    }

    public static synchronized Message SendAnswerHandler(SendResetAnswerMessage sendResetAnswerMessage, String username) {
        if (sendResetAnswerMessage.getAnswer().equals(dataBase.getData().get(username).getAnswerOfTheQuestion()))
            return new CheckResetAnswerMessage(true, dataBase.getData().get(username).getPassword());

        return new CheckResetAnswerMessage(false, "");
    }

    public static synchronized Message changeProfileHandler(ChangeProfileMessage changeProfileMessage, String username) {
        User user = dataBase.getData().get(username);
        if (user != null) {
            user.setFirstName(changeProfileMessage.getFirstName());
            user.setLastName(changeProfileMessage.getLastName());
            user.setBio(changeProfileMessage.getBio());
            user.setBirthDate(changeProfileMessage.getBirthDate());
            user.setProfileImage(changeProfileMessage.getProfileImage());

            System.out.println(username + " changed his/her profile information");
            System.out.println("first name : \"" + changeProfileMessage.getFirstName() + "\"");
            System.out.println("last name : \"" + changeProfileMessage.getLastName() + "\"");
            System.out.println("profile image path : " + changeProfileMessage.getProfileImage());
            System.out.println("bio : \"" + changeProfileMessage.getBio() + "\"");
            System.out.println("birth date : \"" + changeProfileMessage.getBirthDate() + "\"");
            System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

            return new IsProfileChangedMessage(true);
        }

        System.out.println(username + " 's try to change his/her profile is failed");
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        return new IsProfileChangedMessage(false);
    }

    public static synchronized Message setProfileInformation(String username) {
        User user = dataBase.getData().get(username);

        System.out.println(username+ " wants to get his/her profile information...");
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        return new SetProfileInformationMessage(
                user.getUsername(),
                user.getProfileImage(),
                user.getFirstName(),
                user.getLastName(),
                user.getBio(),
                user.getBirthDate(),
                Integer.toString(user.getFollowers().size()),
                Integer.toString(user.getFollowings().size()),
                new ArrayList<>(user.getUserPosts())
        );
    }

    public static synchronized Message setPublishedPost(AskPublishPostMessage askPublishPostMessage, String username) {
        Post post = askPublishPostMessage.getPost();
        User user = dataBase.getData().get(username);

        System.out.println(username + " wants to publish a new post:");
        if (user != null) {
            post.setProfileImagePath(user.getProfileImage());
            post.setUsername(username);
            post.setWriter(user.getFirstName());
            post.setDateAndTime(dateFormatter.format(LocalDateTime.now()));

            user.getUserPosts().add(post);
            user.getAllPosts().add(post);


            for (User u : user.getFollowers()) {
                u.getAllPosts().add(post);
            }

            System.out.println(post);
            System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

            return new PublishPostMessage(true);
        }

        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");
        return new PublishPostMessage(false);
    }

    public static synchronized Message setSearchedProfileInformation(AskSearchMessage askSearchMessage, String username) {
        System.out.println(username + " is searching for : " + askSearchMessage.getSearchedUsername());
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        if (username.equals(askSearchMessage.getSearchedUsername()))
            return new SearchMessage(false);

        User searchUser = dataBase.getData().get(askSearchMessage.getSearchedUsername());
        if (searchUser == null)
            return new SearchMessage(false);

        return new SearchMessage(
                true,
                searchUser.getUsername(),
                searchUser.getProfileImage(),
                searchUser.getFirstName(),
                searchUser.getLastName(),
                searchUser.getBio(),
                searchUser.getBirthDate(),
                Integer.toString(searchUser.getFollowers().size()),
                Integer.toString(searchUser.getFollowings().size()),
                new ArrayList<>(searchUser.getUserPosts()),
                dataBase.getData().get(username).getFollowings().contains(new User(askSearchMessage.getSearchedUsername(), "", "", ""))
        );
    }

    public static synchronized Message handleFollow(AskFollowMessage askFollowMessage, String username) {
        System.out.println(username + " followed " +  askFollowMessage.getUsername());
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        User user = dataBase.getData().get(username);
        User searchedUser = dataBase.getData().get(askFollowMessage.getUsername());

        if (user == null || searchedUser == null)
            return new SetFollowMessage(false);

        user.getFollowings().add(searchedUser);
        searchedUser.getFollowers().add(user);

        // adds users previous post to the new follower
        user.getAllPosts().addAll(searchedUser.getUserPosts());

        return new SetFollowMessage(true);
    }

    public static synchronized Message handleUnfollow(AskUnfollowMessage askUnfollowMessage, String username) {
        System.out.println(username + " unfollow " + askUnfollowMessage.getUsername());
        System.out.println("time : " + dateFormatter.format(LocalDateTime.now()) + "\n");

        User user = dataBase.getData().get(username);
        User searchedUser = dataBase.getData().get(askUnfollowMessage.getUsername());

        if (user == null || searchedUser == null)
            return new SetUnfollowMessage(false);

        user.getFollowings().remove(searchedUser);
        searchedUser.getFollowers().remove(user);

        // deletes users all posts from the unfollowed user
        user.getAllPosts().removeAll(searchedUser.getUserPosts());

        return new SetUnfollowMessage(true);
    }

    public static synchronized Message setTimeLinePosts(String username) {
        User user = dataBase.getData().get(username);

        return new SetTimeLinePostsMessage(new ArrayList<>(user.getAllPosts()));
    }

    public static synchronized Message handleRepost(AskRepostMessage askRepostMessage, String username) {
        User user = dataBase.getData().get(username);

        Post repostedPost = null;
        for (int i = 0; i < user.getAllPosts().size(); i++) {
            if(user.getAllPosts().get(i).equals(askRepostMessage.getRepostedPost())) {
                repostedPost = user.getAllPosts().get(i);
                break;
            }
        }

        if (repostedPost == null || repostedPost.getUsername().equals(username))
            return new SetRepostMessage(false);

        Post post = new Post(
                user.getProfileImage(),
                repostedPost.getPostImagePath(),
                repostedPost.getTitle(),
                repostedPost.getWriter(),
                user.getUsername(),
                repostedPost.getCaption(),
                dateFormatter.format(LocalDateTime.now()),
                0,
                0
        );

        user.getUserPosts().add(post);
        user.getAllPosts().add(post);

        for (User u : user.getFollowers()) {
            u.getAllPosts().add(post);
        }

        repostedPost.repost();

        return new SetRepostMessage(true);
    }
}
