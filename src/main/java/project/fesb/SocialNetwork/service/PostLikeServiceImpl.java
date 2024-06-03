package project.fesb.SocialNetwork.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fesb.SocialNetwork.dao.PostLikeDao;
import project.fesb.SocialNetwork.dao.jpa.app.PostLikeRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.Like;

import java.util.List;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private PostLikeDao postLikeDao;
    private PostLikeRepository postLikeRepository;

    @Autowired
    public PostLikeServiceImpl(PostLikeDao postLikeDao,
                               PostLikeRepository postLikeRepository) {
        this.postLikeDao = postLikeDao;
        this.postLikeRepository = postLikeRepository;
    }

    @Override
    public LikeDto createPostLike(UserDto user, PostDto post) {
        LikeDto postLikeDto = new LikeDto();
        postLikeDto.setUser(user);
        postLikeDto.setPost(post);
        Long postLikeId = postLikeDao.save(postLikeDto);
        postLikeDto.setId(postLikeId);
        return postLikeDto;
    }

    @Override
    public int numberOfLikesOnPost(Long postId) {
        List<LikeDto> likesOnPost = postLikeDao.getLikesByPostId(postId);
        if (likesOnPost == null) {
            return 0;
        }
        return likesOnPost.size();
    }
}
