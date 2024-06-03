package project.fesb.SocialNetwork.service;

import project.fesb.SocialNetwork.model.FollowDto;
import project.fesb.SocialNetwork.model.UserDto;

public interface FollowService {
    int numberOfFollowers(String username);

    FollowDto createFollow(UserDto userFollower, UserDto userFollowee);
}
