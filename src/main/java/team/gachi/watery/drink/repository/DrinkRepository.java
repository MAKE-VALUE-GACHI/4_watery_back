package team.gachi.watery.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.gachi.watery.drink.domain.Drink;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long>, DrinkCustomRepository {
    boolean existsByUserIdAndName(Long userId, String name);

    Optional<Drink> findByIdAndUserId(Long drinkId, Long userId);
}
