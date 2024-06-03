package project.fesb.SocialNetwork.dao;

import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.jpa.app.Like;

import java.util.List;


public interface PostLikeDao {

    Long save(LikeDto postLikeDto);

    List<LikeDto> getLikesByPostId(Long postId);
}
