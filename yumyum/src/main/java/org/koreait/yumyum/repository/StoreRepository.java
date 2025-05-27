package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "select s " +
            "from Store s " +
            "join s.user u " +
            "where u.id = :id")
    Optional<Store> getStoreByUserId(@Param("id") Long id);
}
