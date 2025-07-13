package team.gachi.watery.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.gachi.watery.drink.domain.DrinkHistory;

@Repository
public interface DrinkHistoryRepository extends JpaRepository<DrinkHistory, Long>, DrinkHistoryCustomRepository {
}
