package project.fesb.SocialNetwork.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.api.CreateFollowRequest;
import project.fesb.SocialNetwork.service.FollowService;
import project.fesb.SocialNetwork.service.UserService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @Autowired
    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @PostMapping("/createFollow")
    public Long createFollow(@RequestBody CreateFollowRequest createFollowRequest) {
        UserDto follower = userService.getUserByEmail(createFollowRequest.getFollowerEmail());
        UserDto followee = userService.getUserByEmail(createFollowRequest.getFolloweeEmail());
        System.err.println("emails:" + follower.getEmail() + followee.getEmail());


        return followService.createFollow(follower, followee);
    }
}