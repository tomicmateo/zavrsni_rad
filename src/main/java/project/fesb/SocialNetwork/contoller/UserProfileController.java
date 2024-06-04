package project.fesb.SocialNetwork.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.fesb.SocialNetwork.model.FollowDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.api.CreateUserRequest;
import project.fesb.SocialNetwork.model.api.CreateFollowRequest;
import project.fesb.SocialNetwork.service.UserService;
import project.fesb.SocialNetwork.service.FollowService;


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
    public Long createAppUser(@RequestBody CreateUserRequest createUserRequest) {
        return null; /*Jos nije implentirano*/
    }

    @PostMapping("/createFollow")
    public boolean createFollow(@RequestBody CreateFollowRequest createFollowRequest) {
        UserDto userFollower = userService.getUserById(1L);
        UserDto userFollowee = userService.getUserById(2L);

        FollowDto followDto = followService.createFollow(userFollower, userFollowee);
        return followDto.getId() != null;
    }
}
