package project.fesb.SocialNetwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Follow;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class FollowDto {

    Long id;
    UserDto follower;
    UserDto followee;
    LocalDateTime timestamp;

    public FollowDto (Follow follow)
    {
        this.id = follow.getId();
        this.follower = new UserDto(follow.getFollower());
        this.followee = new UserDto(follow.getFollowee());
        this.timestamp = follow.getTimestamp();
    }
}
