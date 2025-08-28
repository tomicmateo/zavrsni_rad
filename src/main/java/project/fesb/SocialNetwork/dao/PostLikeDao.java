package project.fesb.SocialNetwork.dao;

import project.fesb.SocialNetwork.model.LikeDto;

import java.util.List;


public interface PostLikeDao {

    Long save(LikeDto postLikeDto);

    List<LikeDto> getLikesByPostId(Long postId);
}
