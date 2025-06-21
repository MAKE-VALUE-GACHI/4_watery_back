package team.gachi.watery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gachi.watery.domain.CafeBrand;

public interface CafeBrandRepository extends JpaRepository<CafeBrand, Long>, CafeBrandCustomRepository {
}
