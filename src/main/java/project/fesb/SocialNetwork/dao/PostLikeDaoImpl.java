package project.fesb.SocialNetwork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.dao.jpa.app.PostLikeRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.jpa.app.PostLike;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostLikeDaoImpl implements PostLikeDao{

    private final PostLikeRepository postLikeRepository;
    private final UserDao userDao;
    private final PostDao postDao;

    @Autowired
    public PostLikeDaoImpl(final PostLikeRepository postLikeRepository, UserDao userDao, PostDao postDao) {
        this.postLikeRepository = postLikeRepository;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    /**
     * Method to convert PostLike DTO object to PostLike object which is JPA object
     * @param postLikeDto - data transfer object
     * @return postLike - database JPA object
     */

    private PostLike convertPostLikeDtoToPostLikeObject(LikeDto postLikeDto)
    {
        PostLike postLike = new PostLike();
        if (postLikeDto.getId() != null) {
            postLike = postLikeRepository.findById(postLikeDto.getId()).orElse(new PostLike());
        }
        postLike.setPost(postDao.convertPostDtoToPostObject(postLikeDto.getPost()));
        postLike.setUser(userDao.convertUserDtoToUserObject(postLikeDto.getUser()));
        postLike.setTimestamp(postLikeDto.getTimestamp());
        return postLike;
    }

    @Override
    public List<LikeDto> getLikesByPostId(Long postId) {
        // Convert the PostDto to a Post entity
        PostDto postDto = postDao.findById(postId);
        if (postDto == null) {
            return List.of();
        }
        List<PostLike> postLikes = postLikeRepository.findAllByPost(postDao.convertPostDtoToPostObject(postDto));
        return postLikes.stream().map(LikeDto::new).collect(Collectors.toList());
    }

    @Override
    public Long save(LikeDto postLikeDto) {
        try {
            PostLike postLike = postLikeRepository.save(convertPostLikeDtoToPostLikeObject(postLikeDto));
            return postLike.getPost().getPostId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
