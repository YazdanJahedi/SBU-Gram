public class User {
    final String username;
    final String password;
    final String answerOfTheQuestion;

    public User(String username, String password, String answerOfTheQuestion) {
        this.username = username;
        this.password = password;
        this.answerOfTheQuestion = answerOfTheQuestion;
    }

    Object profileImage;
    String firstName;
    String lastName;
    String bio;

    // todo :   ? string or date class ?
    String birthDate;

    // todo : user[]  -> followers and followings

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

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public Object getProfileImage() {
        return profileImage;
    }
}