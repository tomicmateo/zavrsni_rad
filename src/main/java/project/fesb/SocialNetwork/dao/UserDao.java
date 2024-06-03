package project.fesb.SocialNetwork.dao;

import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.User;

import java.util.List;

public interface UserDao {

    User convertUserDtoToUserObject(UserDto userDto);
    UserDto save(UserDto userDto);
    UserDto getUser(String email);
    UserDto getUserByUsername(String username);
    UserDto getUserById(Long userId);
    List<PostDto> getPostsByUser(String username);
}
