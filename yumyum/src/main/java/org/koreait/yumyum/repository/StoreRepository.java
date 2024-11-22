package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s " +
            "from Store s " +
            "join User u on s.user.id = u.id " +
            "where u.userId = :userId")
    Optional<Store> getStoreByUserId(String userId);
}
