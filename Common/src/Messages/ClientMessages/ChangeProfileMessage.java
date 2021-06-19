package Messages.ClientMessages;

import Messages.Message;

public class ChangeProfileMessage implements Message {
    private final String FirstName;
    private final String LastName;
    private final String bio;
    private final String birthDate;
    private final Object profileImage;

    public ChangeProfileMessage(String firstName, String lastName, String bio, String birthDate, Object profileImage) {
        FirstName = firstName;
        LastName = lastName;
        this.bio = bio;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getBio() {
        return bio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Object getProfileImage() {
        return profileImage;
    }
}
