package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
