package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.MenuOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuOptionDetailRepository extends JpaRepository<MenuOptionDetail, Long> {
    List<MenuOptionDetail> findByMenuOptionId(Long menuOptionId);
}
