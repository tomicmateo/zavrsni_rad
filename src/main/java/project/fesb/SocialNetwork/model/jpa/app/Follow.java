package project.fesb.SocialNetwork.model.jpa.app;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followee_id", referencedColumnName = "user_id", nullable = false)
    private User followee;

}