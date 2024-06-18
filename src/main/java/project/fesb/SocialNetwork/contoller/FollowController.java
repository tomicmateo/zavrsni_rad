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
        System.out.println("Received createFollow request for ID's : " + createFollowRequest.getFollowerId()  + createFollowRequest.getFolloweeId());


        if (createFollowRequest.getFollowerId() == null || createFollowRequest.getFolloweeId() == null) {
            System.out.println("Follower ID and Followee ID must be provided");
        }

        UserDto follower = userService.getUserById(createFollowRequest.getFollowerId());
        UserDto followee = userService.getUserById(createFollowRequest.getFolloweeId());


        Long followId = followService.createFollow(follower, followee);

        return followId;
    }
}