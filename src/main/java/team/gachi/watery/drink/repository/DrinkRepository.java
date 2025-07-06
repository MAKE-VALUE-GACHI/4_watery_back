package team.gachi.watery.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.gachi.watery.drink.model.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
