package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<User, Long> {
}
