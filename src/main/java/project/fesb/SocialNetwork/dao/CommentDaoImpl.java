package project.fesb.SocialNetwork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.dao.jpa.app.CommentRepository;
import project.fesb.SocialNetwork.dao.jpa.app.PostRepository;
import project.fesb.SocialNetwork.model.CommentDto;
import project.fesb.SocialNetwork.model.jpa.app.Comment;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao{

    private final PostDao postDao;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserDao userDao;

    @Autowired
    public CommentDaoImpl(PostDao postDao, CommentRepository commentRepository, PostRepository postRepository, UserDao userDao) {
        this.postDao = postDao;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userDao = userDao;
    }

    /**
     * Method to convert Comment DTO object to Comment object which is JPA object
     * @param commentDto - data transfer object
     * @return comment - database JPA object
     */
    private Comment convertCommentDtoToCommentObject(CommentDto commentDto)
    {
        Comment comment = commentRepository.findByCommentId(commentDto.getId()).orElse(null);
        if(comment == null)
        {
            comment = new Comment();
        }
        comment.setPost(postDao.convertPostDtoToPostObject(commentDto.getPost()));
        comment.setUser(userDao.convertUserDtoToUserObject(commentDto.getUser()));
        comment.setContent(commentDto.getContent());
        comment.setTimestamp(commentDto.getTimestamp());
        return comment;
    }

    @Override
    public Long save(CommentDto commentDto) {
        try
        {
            Comment comment = commentRepository.save(convertCommentDtoToCommentObject(commentDto));
            return comment.getCommentId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<CommentDto> getCommentsOnPost(Long postId) {
        List<CommentDto> postComments = new ArrayList<>();
        Optional<Post> post = postRepository.findByPostId(postId);
        if(post.isPresent())
        {
            List<Comment> commentsOnPost = commentRepository.findAllByPost(post.get());
            for (Comment comment : commentsOnPost){
                postComments.add(new CommentDto(comment));
            }
        }
        return postComments;
    }
}
