package project.fesb.SocialNetwork.service;

import project.fesb.SocialNetwork.model.CommentDto;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;

import java.util.List;

public interface CommentService {

    int numberOfCommentsOnPost(PostDto postDto);

    Long createComment(String content, UserDto userDto, PostDto postDto);

    List<CommentDto> getAllCommentsOnThePost(Long postId);
}
