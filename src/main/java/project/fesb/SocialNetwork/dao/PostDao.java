package project.fesb.SocialNetwork.dao;

import project.fesb.SocialNetwork.model.PostDto;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.util.List;

public interface PostDao {

    Post convertPostDtoToPostObject(PostDto postDto);
    Long save(PostDto PostDto);
    List<PostDto> getPostsByUsername(String username);

    PostDto findById(Long postId);
    List<PostDto> findAll();

}
