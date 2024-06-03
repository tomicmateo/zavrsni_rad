package project.fesb.SocialNetwork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.dao.jpa.app.PostLikeRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.LikeDto;
import project.fesb.SocialNetwork.model.jpa.app.Like;

import java.util.List;

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

    private Like convertPostLikeDtoToPostLikeObject(LikeDto postLikeDto)
    {
        Like postLike = postLikeRepository.findById(postLikeDto.getId()).orElse(null);

        if(postLike == null)
        {
            postLike = new Like();
        }
        postLike.setPost(postDao.convertPostDtoToPostObject(postLikeDto.getPost()));
        postLike.setUser(userDao.convertUserDtoToUserObject(postLikeDto.getUser()));
        postLike.setTimestamp(postLikeDto.getTimestamp());
        return postLike;
    }

    @Override
    public List<LikeDto> getLikesByPostId(Long postId) {
        return null;
    }

    @Override
    public Long save(LikeDto postLikeDto) {
        try
        {
            Like postLike = postLikeRepository.save(convertPostLikeDtoToPostLikeObject(postLikeDto));
            return postLike.getLikeId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
