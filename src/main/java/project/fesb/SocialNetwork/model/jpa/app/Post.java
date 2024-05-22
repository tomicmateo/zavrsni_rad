package project.fesb.SocialNetwork.model.jpa.app;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table (name = "POST")

public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private User user;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime timestamp;


    /* TREBAS LI JOS NAPRAVIT ZA COLUMNS LIKES_COUNT I COMMENTS_COUNT???*/
}
