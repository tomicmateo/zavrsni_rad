package project.fesb.SocialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fesb.SocialNetwork.dao.CommentDao;
import project.fesb.SocialNetwork.model.CommentDto;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {

        this.commentDao = commentDao;
    }

    @Override
    public int numberOfCommentsOnPost(PostDto postDto) {
        List<CommentDto> commentsOnPost = commentDao.getCommentsOnPost(postDto.getId());
        return commentsOnPost.size();
    }

    @Override
    public Long createComment(String content, UserDto userDto, PostDto postDto) {
        CommentDto commentDto = new CommentDto();
        System.err.println("follow id: " + commentDto.getId());

        commentDto.setContent(content);
        commentDto.setUser(userDto);
        commentDto.setPost(postDto);

        return commentDao.save(commentDto);
    }

    @Override
    public List<CommentDto> getAllCommentsOnThePost(Long postId) {
        return commentDao.getCommentsOnPost(postId);
    }
}
