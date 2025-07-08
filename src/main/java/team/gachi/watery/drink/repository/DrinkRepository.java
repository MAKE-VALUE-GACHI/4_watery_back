package team.gachi.watery.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.gachi.watery.drink.domain.Drink;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findAllByUserId(Long userId);

    boolean existsByUserIdAndName(Long userId, String name);
}
