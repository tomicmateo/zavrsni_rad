package project.fesb.SocialNetwork.service;

import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;

public interface PostLikeService {
    int numberOfLikesOnPost(Long postId);

    Long createPostLike(UserDto user, PostDto post);
}
