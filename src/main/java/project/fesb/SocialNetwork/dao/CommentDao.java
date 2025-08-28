package project.fesb.SocialNetwork.dao;

import project.fesb.SocialNetwork.model.CommentDto;

import java.util.List;

public interface CommentDao {
    Long save(CommentDto CommentDto);
    List<CommentDto> getCommentsOnPost(Long postId);
}
