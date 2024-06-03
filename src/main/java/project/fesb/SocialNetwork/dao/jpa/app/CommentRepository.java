package project.fesb.SocialNetwork.dao.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.model.jpa.app.Comment;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCommentId(Long commentId);
    List<Comment> findAllByUser(User user);

    List<Comment> findAllByPost(Post post);

    List<Comment> findAll();

    Comment save(Comment comment);
}
