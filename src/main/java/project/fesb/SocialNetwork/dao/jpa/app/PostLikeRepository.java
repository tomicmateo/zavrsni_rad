package project.fesb.SocialNetwork.dao.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.model.jpa.app.Like;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<Like, Long>{

    List<Like> findAllByUser(User user);
    List<Like> findAllByPost(Post post);
    List<Like> findAll();

    Optional<Like> findById(Long id);

    Like save(Like postLike);
}
