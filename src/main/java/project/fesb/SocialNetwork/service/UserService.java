package project.fesb.SocialNetwork.service;

import project.fesb.SocialNetwork.model.UserDto;

public interface UserService {
    int numberOfPostsByUser (String username);
    UserDto getUserByUsername(String username);

    UserDto getUserById(Long userId);
    UserDto getUserByEmail(String email);
    UserDto createUser(String username, String bio, String email, String password, byte[] profilePicture);
}
