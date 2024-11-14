package org.koreait.yumyum.repository;


import org.koreait.yumyum.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT a.menuName, a.imageUrl, a.menuDescription, a.menuPrice, a.isAvailable, b.menuCategory " +
            "from Menu as a  " +
            "join MenuCategory as b ON a.menuCategory.id = b.id"
    )
    List<Object[]> findAllMenu();
}