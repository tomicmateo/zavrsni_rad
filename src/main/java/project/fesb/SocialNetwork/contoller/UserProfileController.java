package project.fesb.SocialNetwork.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.fesb.SocialNetwork.model.FollowDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.api.CreateUserRequest;
import project.fesb.SocialNetwork.model.api.CreateFollowRequest;
import project.fesb.SocialNetwork.service.UserService;
import project.fesb.SocialNetwork.service.FollowService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    private final UserService userService;
    private final FollowService followService;

    @Autowired
    public UserProfileController(UserService userService,
                                 FollowService followService) {
        this.userService = userService;
        this.followService = followService;
    }

    @GetMapping("/user/{userEmail}")
    public UserDto getUser(@PathVariable("userEmail") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/createUser")
    public Long createUser(@RequestParam("username") String username,
                           @RequestParam("bio") String bio,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {
        byte[] profilePictureBytes = null;
        if (profilePicture != null) {
            try {
                profilePictureBytes = profilePicture.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UserDto newUser = userService.createUser(username, bio, email, password, profilePictureBytes);
        return newUser.getId();
    }

}
