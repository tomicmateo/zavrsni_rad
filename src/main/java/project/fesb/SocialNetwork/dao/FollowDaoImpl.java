package project.fesb.SocialNetwork.dao;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.dao.jpa.app.FollowRepository;
import project.fesb.SocialNetwork.model.FollowDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Follow;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FollowDaoImpl implements FollowDao{

    private final FollowRepository followRepository;
    private final UserDao userDao;

    @Autowired
    public FollowDaoImpl(final FollowRepository followRepository, final UserDao userDao) {
        this.followRepository = followRepository;
        this.userDao = userDao;
    }

    /**
     * Method to convert Follow DTO object to Follow object which is JPA object
     * @param followDto - data transfer object
     * @return follow - database JPA object
     */
    private Follow convertFollowDtoToFollowObject(FollowDto followDto)
    {
        Follow follow = null;
        if (followDto.getId() != null) {
            follow = followRepository.findById(followDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Follow not found"));
        }
        if(follow == null)
        {
            follow = new Follow();
        }
        follow.setFollower(userDao.convertUserDtoToUserObject(followDto.getFollower()));
        follow.setFollowee(userDao.convertUserDtoToUserObject(followDto.getFollowee()));
        if(followDto.getId() != null) {
            follow.setId(followDto.getId());
        }
        return follow;
    }

    @Override
    public List<UserDto> getFollowersForUsername(String username) {
        UserDto userWithUsername = userDao.getUserByUsername(username);
        List<Follow> followListForUsername = followRepository.findAllByFollowee(userDao.convertUserDtoToUserObject(userWithUsername));
        return followListForUsername
                .stream()
                .map(f -> new UserDto(f.getFollower()))
                .collect(Collectors.toList());
    }

    @Override
    public Long save(FollowDto followDto) {
        try
        {
            Follow follow = followRepository.save(convertFollowDtoToFollowObject(followDto));
            return follow.getId();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
