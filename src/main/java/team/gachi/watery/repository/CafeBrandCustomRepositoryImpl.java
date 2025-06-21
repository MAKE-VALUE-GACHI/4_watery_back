package team.gachi.watery.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.gachi.watery.domain.CafeBrand;

import java.util.List;

import static team.gachi.watery.domain.QCafeBrand.cafeBrand;

@Repository
@RequiredArgsConstructor
public class CafeBrandCustomRepositoryImpl implements CafeBrandCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CafeBrand> findAllByParam(String name) {
        return queryFactory.select(cafeBrand)
                .from(cafeBrand)
                .where(
                        name != null ? cafeBrand.name.containsIgnoreCase(name) : null
                )
                .fetch();
    }
}
