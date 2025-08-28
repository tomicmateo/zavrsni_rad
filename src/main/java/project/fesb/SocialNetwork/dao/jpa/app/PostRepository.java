package project.fesb.SocialNetwork.dao.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    Optional<Post> findByPostId(long postId);
    List<Post> findAllByUser(User user);
    List<Post> findAll();
    List<Post> findByTimestampBetween(LocalDateTime start, LocalDateTime end);


    Post save(Post post);
}
