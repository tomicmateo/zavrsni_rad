package project.fesb.SocialNetwork.dao;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.dao.jpa.app.UserRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.User;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Autowired
    public UserDaoImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to convert User DTO object to User object which is JPA object
     *
     * @param userDto - data transfer object
     * @return user - database JPA object
     */

    public User convertUserDtoToUserObject(UserDto userDto) {
        User user = userRepository.findByUserId(userDto.getId()).orElse(null);
        if (user == null) {
            user = new User();
        }
        user.setUsername(userDto.getUsername());
        user.setBio(userDto.getBio());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserId(userDto.getId());
        return user;
    }


    @Override
    public UserDto save(UserDto userDto) {
        try {
            User appUser = userRepository.save(convertUserDtoToUserObject(userDto));
            userDto.setId(appUser.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDto;
    }

    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new UserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findByUserId(userId).orElse(null);
        assert user != null;
        return new UserDto(user);
    }

    @Override
    public List<PostDto> getPostsByUser(String username) {
        return null;
    }

}
