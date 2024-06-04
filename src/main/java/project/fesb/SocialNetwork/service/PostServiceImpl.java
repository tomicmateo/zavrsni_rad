package project.fesb.SocialNetwork.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fesb.SocialNetwork.dao.UserDao;
import project.fesb.SocialNetwork.dao.PostDao;
import project.fesb.SocialNetwork.dao.jpa.app.PostRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final UserDao userDao;
    private final PostDao postDao;

    @Autowired
    public PostServiceImpl(UserDao userDao, PostDao postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public PostDto updatePostContent(Long postId, String newPostContent) {

        /*Implementation is missing

        Ovo dole bi mozda trebalo raditi
        // Fetch the post by its ID
        PostDto postDto = postDao.findById(postId);

        if (postDto == null) {
            throw new EntityNotFoundException("Post with ID " + postId + " not found");
        }

        // Update the post content
        postDto.setContent(newPostContent);

        // Save the updated post
        Long updatedPostId = postDao.save(postDto);

        // Return the updated PostDto
        return postDao.findById(updatedPostId);
        */


        return null;
    }

    @Override
    public List<PostDto> getAllPostDetails() {
        return postDao.findAll(); // Fetch all posts from the repository
    }

    @Override
    public PostDto createPost(String newPostContent, UserDto user) {
        PostDto postDto = new PostDto();
        postDto.setContent(newPostContent);
        postDto.setUser(user);
        Long postId = postDao.save(postDto);
        postDto.setId(postId);
        return postDto;
    }

    public PostDto getPostById(Long postId) {
        if (postId == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }
        return postDao.findById(postId);
    }
}
