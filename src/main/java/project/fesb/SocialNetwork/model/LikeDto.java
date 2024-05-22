package project.fesb.SocialNetwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Post;
import project.fesb.SocialNetwork.model.jpa.app.Like;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class LikeDto {

    Long id;
    PostDto post;
    UserDto user;
    LocalDateTime timestamp;

    public LikeDto (Like Like)
    {
        this.setId(Like.getLikeId());
        this.setUser(new UserDto(Like.getUser()));
        this.setPost(new PostDto(Like.getPost()));
        this.setTimestamp(Like.getTimestamp());
    }
}
