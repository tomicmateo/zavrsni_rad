package project.fesb.SocialNetwork.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.api.CreatePostLikeRequest;
import project.fesb.SocialNetwork.service.PostLikeService;
import project.fesb.SocialNetwork.service.PostService;
import project.fesb.SocialNetwork.service.UserService;

@RestController
@RequestMapping("/api/postLike")
@CrossOrigin(origins = "http://localhost:3000")
public class PostLikeController {
    private final UserService userService;
    private final PostService postService;
    private final PostLikeService postLikeService;

    @Autowired
    public PostLikeController(UserService userService, PostService postService, PostLikeService postLikeService) {
        this.userService = userService;
        this.postService = postService;
        this.postLikeService = postLikeService;
    }

    @PostMapping("/create")
    public Long createPostLike(@RequestBody CreatePostLikeRequest createPostLikeRequest) {
        System.out.println("Received createPostLike request for userEmail: " + createPostLikeRequest.getUserEmail() + ", postId: " + createPostLikeRequest.getPostId());

        UserDto userDto = userService.getUserByEmail((createPostLikeRequest.getUserEmail()));
        if (userDto == null) {
            System.out.println("User not found with email: " + createPostLikeRequest.getUserEmail());
            return null;
        }
        else {
            System.out.println("User  found with email: " + createPostLikeRequest.getUserEmail());
            System.out.println("User  found with username: " + userDto.getUsername());

        }

        PostDto postDto = postService.getPostById(createPostLikeRequest.getPostId());
        if (postDto == null) {
            System.out.println("Post not found with ID: " + createPostLikeRequest.getPostId());
            return null;
        }
        else {
            System.out.println("Post  found with ID: " + createPostLikeRequest.getPostId());
            System.out.println("Post  found with content: " + postDto.getContent());
        }
        return postLikeService.createPostLike(userDto, postDto);
    }

    @GetMapping("/count/{postId}")
    public int getNumberOfLikes(@PathVariable("postId") Long postId) {
        return postLikeService.numberOfLikesOnPost(postId);
    }
}
