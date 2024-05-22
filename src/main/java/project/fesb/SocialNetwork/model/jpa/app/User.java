package project.fesb.SocialNetwork.model.jpa.app;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USERNAME")
    private String username;

   @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column (name = "BIO")
    private String bio;

    @Lob
    @Column(name = "PROFILE_PICTURE")
    private byte[] profilePicture;

}