package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.MenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long> {
}
