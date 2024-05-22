package project.fesb.SocialNetwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fesb.SocialNetwork.model.jpa.app.User;

@Getter @Setter @NoArgsConstructor
public class UserDto {

    Long id;
    String username;
    String bio;
    String email;
    String password;
    byte[] profilePicture;

    public UserDto (User user)
    {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profilePicture = user.getProfilePicture();
    }
}
