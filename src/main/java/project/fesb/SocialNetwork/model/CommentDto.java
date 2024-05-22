package project.fesb.SocialNetwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fesb.SocialNetwork.model.jpa.app.User;
import project.fesb.SocialNetwork.model.jpa.app.Comment;
import project.fesb.SocialNetwork.model.jpa.app.Post;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class CommentDto {

    Long id;
    String content;
    UserDto user;
    PostDto post;
    LocalDateTime timestamp;

    public CommentDto(Comment comment)
    {
        this.id = comment.getCommentId();
        this.user = new UserDto(comment.getUser());
        this.post = new PostDto(comment.getPost());
        this.content= comment.getContent();
        this.timestamp = comment.getTimestamp();
    }
}
