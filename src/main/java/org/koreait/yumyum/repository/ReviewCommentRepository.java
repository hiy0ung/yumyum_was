package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    @Query(value = "SELECT rc " +
    "FROM ReviewComment rc " +
    "JOIN rc.review r " +
    "where r.id = :reviewId")
    Optional<ReviewComment> findReviewCommentByReviewId(@Param("reviewId") Long reviewId);
}
