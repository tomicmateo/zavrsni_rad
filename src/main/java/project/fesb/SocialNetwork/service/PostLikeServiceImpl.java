package project.fesb.SocialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fesb.SocialNetwork.dao.PostLikeDao;
import project.fesb.SocialNetwork.dao.jpa.app.PostLikeRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.UserDto;

import java.util.List;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private PostLikeDao postLikeDao;
    private final PostLikeRepository postLikeRepository;

    @Autowired
    public PostLikeServiceImpl(PostLikeDao postLikeDao, final
                               PostLikeRepository postLikeRepository) {
        this.postLikeDao = postLikeDao;
        this.postLikeRepository = postLikeRepository;
    }

    @Override
    public Long createPostLike(UserDto userDto, PostDto postDto) {
        LikeDto likeDto = new LikeDto();
        System.err.println("like id: " + likeDto.getId());

        likeDto.setUser(userDto);
        likeDto.setPost(postDto);

        return postLikeDao.save(likeDto);
    }

    @Override
    public int numberOfLikesOnPost(Long postId) {
        List<LikeDto> likesOnPost = postLikeDao.getLikesByPostId(postId);
        return likesOnPost != null ? likesOnPost.size() : 0;
    }
}
