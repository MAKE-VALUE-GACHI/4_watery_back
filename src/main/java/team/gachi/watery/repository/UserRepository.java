package team.gachi.watery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gachi.watery.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialTypeAndSocialId(User.SocialType socialType, String socialId);
}
