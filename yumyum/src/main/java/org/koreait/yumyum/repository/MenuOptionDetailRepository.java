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
    List<MenuOptionDetail> findIdByMenuOptionId(Long menuOptionId);

    @Transactional
    @Modifying
    @Query(value = """
    update 
        menu_option_details
    set
        option_detail_name = :optionDetailName,
        additional_fee = :additionalFee
    where
        id = :id and
        menu_option_id = :menuOptionId
""", nativeQuery = true)
    void update(@Param("optionDetailName") String optionDetailName,
                @Param("additionalFee") int additionalFee,
                @Param("id") Long id,
                @Param("menuOptionId") Long menuOptionId);

}
