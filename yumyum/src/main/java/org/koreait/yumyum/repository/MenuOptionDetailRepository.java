package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.MenuOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuOptionDetailRepository extends JpaRepository<MenuOptionDetail, Long> {
}
