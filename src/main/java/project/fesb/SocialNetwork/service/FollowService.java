package project.fesb.SocialNetwork.service;

import project.fesb.SocialNetwork.model.UserDto;

public interface FollowService {
    int numberOfFollowers(String username);

    Long createFollow(UserDto userFollower, UserDto userFollowee);
}
