package project.fesb.SocialNetwork.dao;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.dao.jpa.app.PostRepository;
import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.UserDto;
import project.fesb.SocialNetwork.model.jpa.app.Post;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostDaoImpl implements PostDao{

    private final UserDao userDao;
    private final PostRepository postRepository;


    @Autowired
    public PostDaoImpl(UserDao userDao, PostRepository postRepository) {
        this.userDao = userDao;
        this.postRepository = postRepository;
    }

    /**
     * Method to convert Post DTO object to Post object which is JPA object
     * @param postDto - data transfer object
     * @return post - database JPA object
     */

    public Post convertPostDtoToPostObject(PostDto postDto)
    {
        Post post = new Post();
        if (postDto.getId() != null) {
            post = postRepository.findByPostId(postDto.getId()).orElse(new Post());
        }
        post.setUser(userDao.convertUserDtoToUserObject(postDto.getUser()));
        post.setContent(postDto.getContent());
        post.setTimestamp(postDto.getTimestamp());
        return post;
    }

    @Override
    public Long save(PostDto postDto) {
        try
        {
            Post post = postRepository.save(convertPostDtoToPostObject(postDto));
            postDto.setId(post.getPostId());
            return post.getPostId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PostDto> getPostsByUsername(String username) {
        UserDto user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("User with username " + username + " not found");
        }
        List<Post> postsByUser = postRepository.findAllByUser(userDao.convertUserDtoToUserObject(user));
        return postsByUser.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto findById(Long id) {
        Post post = postRepository.findByPostId(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with given ID not found"));

        return new PostDto(post);
    }

    @Override
    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }
}
