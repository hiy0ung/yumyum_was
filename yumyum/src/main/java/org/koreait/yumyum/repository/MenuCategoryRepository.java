package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.MenuCategory;
import org.koreait.yumyum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    List<MenuCategory> findByMenuCategorySequenceBetween(int start, int end);
    List<MenuCategory> findAllByOrderByMenuCategorySequenceAsc();
    List<MenuCategory> findAllCategoryByStoreId(Long id);
}
