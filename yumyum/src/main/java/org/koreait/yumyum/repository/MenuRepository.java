package org.koreait.yumyum.repository;


import org.koreait.yumyum.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = """
        SELECT
            m.id AS menuId,
            m.menu_name as menuName,
            m.menu_price as menuPrice,
            m.image_url as imageUrl,
            m.menu_description as menuDescription,
            m.is_available as isAvailable,
            mc.menu_category as menuCategory,
            mo.id AS menuOptionId,
            mo.option_name as optionName,
            md.id AS detailId,
            md.option_detail_name as optionDetailName,
            md.additional_fee as addtionalFee
        FROM
            menus m
        INNER JOIN
            menu_categories mc ON m.category_id = mc.id
        INNER JOIN
            menu_option_group mog ON mog.menu_id = m.id
        INNER JOIN
            menu_options mo ON mog.menu_option_id = mo.id
        INNER JOIN
            menu_option_details md ON md.menu_option_id = mo.id
        ORDER BY
            m.id, mo.id, md.id;
""", nativeQuery = true)
    List<Object[]> findAllMenuWithCategoryAndOption();


    @Query(value = """
        SELECT
            m.id AS menuId,
            m.menu_name as menuName,
            m.menu_price as menuPrice,
            m.image_url as imageUrl,
            m.menu_description as menuDescription,
            m.is_available as isAvailable,
            mc.menu_category as menuCategory,
            mo.id AS menuOptionId,
            mo.option_name as optionName,
            md.id AS detailId,
            md.option_detail_name as optionDetailName,
            md.additional_fee as addtionalFee
        FROM
            menus m
        INNER JOIN
            menu_categories mc ON m.category_id = mc.id
        INNER JOIN
            menu_option_group mog ON mog.menu_id = m.id
        INNER JOIN
            menu_options mo ON mog.menu_option_id = mo.id
        INNER JOIN
            menu_option_details md ON md.menu_option_id = mo.id
        WHERE 
            m.id = :menuId
        ORDER BY
            m.id, mo.id, md.id;
""", nativeQuery = true)
    List<Object[]> findMenuWithCategoryAndOptionByMenuId(@Param("menuId") Long id);
}