package team.gachi.watery.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gachi.watery.drink.domain.ColorTemplate;

public interface ColorTemplateRepository extends JpaRepository<ColorTemplate, Long> {
}
