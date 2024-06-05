package project.fesb.SocialNetwork.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.api.CreateCommentRequest;
import project.fesb.SocialNetwork.model.api.CreatePostLikeRequest;
import project.fesb.SocialNetwork.model.api.CreatePostRequest;
import project.fesb.SocialNetwork.service.UserService;
import project.fesb.SocialNetwork.service.CommentService;
import project.fesb.SocialNetwork.service.PostLikeService;
import project.fesb.SocialNetwork.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final UserService userService;
    private final PostService postService;
    private final PostLikeService postLikeService;
    private final CommentService commentService;

    @Autowired
    public PostController(UserService userService,
                          PostService postService,
                          PostLikeService postLikeService,
                          CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
        this.postLikeService = postLikeService;
    }

    @GetMapping("/list")
    public List<PostDto> getPostList() {
        String username;
        String avatar;
        List<PostDto> postDtoList = postService.getAllPostDetails();
        for (PostDto postDto : postDtoList) {
            int numberOfLikes = postLikeService.numberOfLikesOnPost(postDto.getId());
            postDto.setNumberOfLikes(numberOfLikes);

            //int numberOfComments = 8;
        }
        //TODO: napravit novi objekt u /api koji vraca post details. Npr, od usera nam treba samo username i avatar
        return postDtoList;
    }


    @PostMapping("/create")
    public String createPost(@RequestBody CreatePostRequest createPostRequest) {
        UserDto user = userService.getUserByEmail("tomela@fesb.hr");
        PostDto postDto = postService.createPost(createPostRequest.getContent(), user);
        return postDto.getContent();
    }

    @PostMapping("/createPostLike")
    public boolean createPostLike(@RequestBody CreatePostLikeRequest createPostLikeRequest){
        UserDto user = userService.getUserById(1L);
        PostDto post = postService.getPostById(1L);

        LikeDto likeDto = postLikeService.createPostLike(user, post);
        return true;
    }
}
