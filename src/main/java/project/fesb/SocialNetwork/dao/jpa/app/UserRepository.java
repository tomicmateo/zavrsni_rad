package project.fesb.SocialNetwork.dao.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fesb.SocialNetwork.model.jpa.app.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    Optional<User> findByUserId(Long userId);

    List<User> findAll();

    User findByUsername(String username);

    User save(User user);
}
