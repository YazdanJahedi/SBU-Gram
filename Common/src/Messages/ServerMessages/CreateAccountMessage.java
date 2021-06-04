package Messages.ServerMessages;

import Messages.Message;

public class CreateAccountMessage implements Message {
    private boolean isUsernameValid = true;
    private boolean isPasswordValid = true;
    private boolean isConfirmPasswordValid = true;

    public void setUsernameValid(boolean usernameValid) {
        isUsernameValid = usernameValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        isPasswordValid = passwordValid;
    }

    public void setConfirmPasswordValid(boolean confirmPasswordValid) {
        isConfirmPasswordValid = confirmPasswordValid;
    }

    public boolean isUsernameValid() {
        return isUsernameValid;
    }

    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public boolean isConfirmPasswordValid() {
        return isConfirmPasswordValid;
    }
}
