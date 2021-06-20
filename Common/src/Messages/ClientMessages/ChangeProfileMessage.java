package Messages.ClientMessages;

import Messages.Message;

public class ChangeProfileMessage implements Message {
    private final String firstName;
    private final String lastName;
    private final String bio;
    private final String birthDate;
    private final String profileImage;

    public ChangeProfileMessage(String firstName, String lastName, String bio, String birthDate, String profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birthDate = birthDate;
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
}
