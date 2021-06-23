package Messages.ServerMessages.HomePageMessages;

import Messages.Message;

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

    public SearchMessage(boolean isUserFound, String username, String profileImagePath,
                         String firstName, String lastName, String bio, String birthDate) {
        this.isUserFound = isUserFound;
        this.username = username;
        this.profileImagePath = profileImagePath;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birthDate = birthDate;
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
}
