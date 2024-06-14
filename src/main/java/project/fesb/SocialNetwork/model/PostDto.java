package project.fesb.SocialNetwork.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*import project.fesb.SocialNetwork.model.jpa.app.Hashtag;  TRIBAT CE MOZDA KASNIJE*/
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private Long id;
    private String content;
    private UserDto user;
    private LocalDateTime timestamp;
    private int numberOfLikes;

    public PostDto (Post post)
    {
        this.setId(post.getPostId());
        this.setContent(post.getContent());
        this.setUser(new UserDto(post.getUser()));
        this.setTimestamp(post.getTimestamp());
    }

    public Post toEntity() {
        Post post = new Post();
        post.setPostId(this.getId());
        post.setContent(this.getContent());
        post.setTimestamp(this.getTimestamp());
        post.setUser(this.getUser().toEntity());
        // set other fields as necessary
        return post;
    }
    /*public PostDto (Post post, List<Hashtag> hashtags)
    {
        this.setId(post.getPostId());
        this.setPostText(post.getPostText());
        this.setUser(new UserDto(post.getUser()));
        this.setCreatedAt(post.getCreatedAt());
        this.setHashtags(hashtags);
    }*/

}
