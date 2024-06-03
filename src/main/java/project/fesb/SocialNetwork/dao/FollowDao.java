package project.fesb.SocialNetwork.dao;

import project.fesb.SocialNetwork.model.FollowDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.Follow;

import java.util.List;

public interface FollowDao {
    List<UserDto> getFollowersForUsername(String username);

    Long save(FollowDto followDto);
}
