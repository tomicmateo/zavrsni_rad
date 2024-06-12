package project.fesb.SocialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fesb.SocialNetwork.dao.FollowDao;
import project.fesb.SocialNetwork.model.FollowDto;
import project.fesb.SocialNetwork.model.UserDto;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;

    @Autowired
    public FollowServiceImpl(FollowDao followDao) {
        this.followDao = followDao;
    }

    @Override
    public int numberOfFollowers(String username) {
        List<UserDto> usersFollowing = followDao.getFollowersForUsername(username);
        return usersFollowing.size();
    }

    @Override
    public Long createFollow(UserDto follower, UserDto followee) {

        FollowDto followDto = new FollowDto();
        System.err.println("follow id: " + followDto.getId());


        followDto.setFollower(follower);
        followDto.setFollowee(followee);
        System.err.println("emails, follow :" + followDto.getFollower().getEmail() + followDto.getFollowee().getEmail());


        return followDao.save(followDto);
    }
}
