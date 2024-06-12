package project.fesb.SocialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fesb.SocialNetwork.dao.UserDao;
import project.fesb.SocialNetwork.dao.jpa.app.UserRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDao userDao, final UserRepository userRepository) {

        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    @Override
    public int numberOfPostsByUser (String username) {
        List<PostDto> postsByUser = userDao.getPostsByUser(username);
        return postsByUser.size();
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserDto user = userDao.getUser(email);

        if (user == null) {
            System.err.println("Error: No user found with email: " + email);
        }
        return user;
    }

    @Override
    public UserDto createUser(String username, String bio, String email, String password, byte[] profilePicture) {
        User user = new User();
        user.setUsername(username);
        user.setBio(bio);
        user.setEmail(email);
        user.setPassword(password); // Remember to hash the password before saving
        user.setProfilePicture(profilePicture);

        User savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return new UserDto(user);
    }
}
