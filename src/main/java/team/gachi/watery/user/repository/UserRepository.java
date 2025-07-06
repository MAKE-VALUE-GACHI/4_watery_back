package team.gachi.watery.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gachi.watery.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialTypeAndSocialId(User.SocialType socialType, String socialId);
}
