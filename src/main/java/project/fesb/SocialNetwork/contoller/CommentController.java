package project.fesb.SocialNetwork.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.fesb.SocialNetwork.model.CommentDto;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.api.CreateCommentRequest;
import project.fesb.SocialNetwork.service.UserService;
import project.fesb.SocialNetwork.service.CommentService;
import project.fesb.SocialNetwork.service.PostLikeService;
import project.fesb.SocialNetwork.service.PostService;

import java.util.List;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final UserService userService;
    private final PostService postService;
    private final PostLikeService postLikeService;
    private final CommentService commentService;

    @Autowired
    public CommentController(UserService userService,
                             PostService postService,
                             PostLikeService postLikeService,
                             CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
        this.postLikeService = postLikeService;
    }

    @GetMapping("/list")
    public List<CommentDto> getCommentList(Long postId) {
        List<CommentDto> commentDtoList = commentService.getAllCommentsOnThePost(postId);

        return commentDtoList;
    }

    @PostMapping("/create")
    public boolean createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        UserDto userDto = userService.getUserByEmail(createCommentRequest.getUserEmail());
        PostDto postDto = postService.getPostById(createCommentRequest.getPostId());
        return commentService.createComment(createCommentRequest.getCommentText(), userDto, postDto) != null;
    }

}
