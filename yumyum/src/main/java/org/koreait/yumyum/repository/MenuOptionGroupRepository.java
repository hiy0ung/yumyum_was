package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.MenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long> {
    @Query(value = """
select
	m.menu_option_id
FROM
	menu_option_group m
WHERE
	m.menu_id = :optionId;
""", nativeQuery = true)
    Optional<List<Long>> findMenuOptionIdByMenuId(Long optionId);
}
