package project.fesb.SocialNetwork.model.jpa.app;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID", nullable = false)
    private Post post;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime timestamp;
}