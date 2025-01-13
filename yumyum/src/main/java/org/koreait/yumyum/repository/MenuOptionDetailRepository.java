package org.koreait.yumyum.repository;

import jakarta.transaction.Transactional;
import org.koreait.yumyum.entity.MenuOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MenuOptionDetailRepository extends JpaRepository<MenuOptionDetail, Long> {
    List<MenuOptionDetail> findByMenuOptionId(Long menuOptionId);

    @Query(value = """
    SELECT
        id
    FROM
        menu_option_details
    where
        menu_option_id = :menuOptionId
""", nativeQuery = true)
    List<Long> findIdByMenuOptionId(@Param("menuOptionId") Long menuOptionId);

    @Query(value = """
    SELECT
        id,
        menu_option_id,
        option_detail_name,
        additional_fee
    FROM
        menu_option_details
    where
        menu_option_id = :menuOptionId and
        id = :pkId
""", nativeQuery = true)
    List<MenuOptionDetail> findByMenuOptionIdAndId(@Param("menuOptionId") Long menuOptionId, @Param("pkId") Long pkId);

}
