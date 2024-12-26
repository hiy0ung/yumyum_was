package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "select s " +
            "from Store s " +
            "join s.user u " +
            "where u.userId = :userId")
    Optional<Store> getStoreByUserId(String userId);
}
