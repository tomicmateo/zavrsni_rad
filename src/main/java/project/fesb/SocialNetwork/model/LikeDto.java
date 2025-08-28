package project.fesb.SocialNetwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fesb.SocialNetwork.model.jpa.app.PostLike;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class LikeDto {

    Long id;
    PostDto post;
    UserDto user;
    LocalDateTime timestamp;

    public LikeDto (PostLike PostLike)
    {
        this.setId(PostLike.getLikeId());
        this.setUser(new UserDto(PostLike.getUser()));
        this.setPost(new PostDto(PostLike.getPost()));
        this.setTimestamp(PostLike.getTimestamp());
    }
}
