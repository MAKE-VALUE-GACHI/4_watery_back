package team.gachi.watery.drink.repository;


import team.gachi.watery.common.Status;
import team.gachi.watery.drink.domain.Drink;

import java.util.List;

public interface DrinkCustomRepository {
    List<Drink> findAllBy(Long userId, Status status);
}
