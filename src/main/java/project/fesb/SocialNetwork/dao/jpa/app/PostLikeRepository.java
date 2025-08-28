package project.fesb.SocialNetwork.dao.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.jpa.app.PostLike;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>{

    List<PostLike> findAllByUser(User user);
    List<PostLike> findAllByPost(Post post);
    List<PostLike> findAll();

    Optional<PostLike> findById(Long id);

    PostLike save(PostLike postLike);

    List<PostLike> findAllByUser_UserId(Long userId);
    List<PostLike> findAllByPost_PostId(Long postId);
}
