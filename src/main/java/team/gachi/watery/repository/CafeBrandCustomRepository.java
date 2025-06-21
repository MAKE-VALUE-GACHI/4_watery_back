package team.gachi.watery.repository;

import team.gachi.watery.domain.CafeBrand;

import java.util.List;

public interface CafeBrandCustomRepository {
    List<CafeBrand> findAllByParam(String name);
}
