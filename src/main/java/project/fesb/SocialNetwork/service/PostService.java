package project.fesb.SocialNetwork.service;

import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.List;

public interface PostService {
    PostDto updatePostContent(Long postId, String newPostContent);
    List<PostDto> getAllPostDetails();
    PostDto createPost(String newPostContent, UserDto user);

    PostDto getPostById(Long id);
}
