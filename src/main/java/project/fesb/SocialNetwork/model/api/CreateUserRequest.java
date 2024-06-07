package project.fesb.SocialNetwork.model.api;

public class CreateUserRequest {
    private String username;
    private String bio;
    private String email;
    private String password;
    private byte[] profilePicture;

    public String getUsername() {
        return username;
    }
    public String getBio() {
        return bio;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
