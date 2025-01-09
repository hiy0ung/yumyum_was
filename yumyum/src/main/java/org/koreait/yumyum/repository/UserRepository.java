package org.koreait.yumyum.repository;


import jakarta.validation.constraints.NotNull;
import org.koreait.yumyum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserBusinessNumber(String userBusinessNumber);

    Optional<User> findByUserId(String userId);

    boolean existsByUserIdAndUserNameAndUserEmail(String userId,String userName,String userEmail);

    boolean existsByUserNameAndUserEmail(String userName, String userEmail);

    Optional<User> findByUserNameAndUserEmail(String userName, String userEmail);
}