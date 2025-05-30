package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.ReviewEventNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewNoticeRepository extends JpaRepository<ReviewEventNotice, Long> {

    @Query(value = "select ren " +
    "from ReviewEventNotice ren " +
    "join ren.store s " +
    "where s.id = :storeId")
    Optional<ReviewEventNotice> getReviewEventNoticeByStoreId(Long storeId);
}
