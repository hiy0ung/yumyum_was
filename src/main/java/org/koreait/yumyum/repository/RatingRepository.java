package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r.rating AS rating, COUNT(r.id) AS reviewCount " +
            "FROM Review r " +
            "LEFT JOIN r.order o ON r.order.id = o.id " +
            "WHERE o.store.id = :storeId " +
            "GROUP BY r.rating ")
    List<Object[]> findReviewCountByRating(@Param("storeId") Long storeId);


    @Query("SELECT CONCAT(YEAR(r.reviewDate), '-', MONTH(r.reviewDate)) as reviewMonth, " +
            "CAST(ROUND(AVG(r.rating), 1) AS DOUBLE) avgRating, " +
            "CAST(COUNT(r) AS INTEGER) as reviewCount " +
            "FROM Review r " +
            "JOIN Order o ON r.order.id = o.id " +
            "WHERE o.store.id = :storeId " +
            "AND r.reviewDate between :startDate AND :endDate " +
            "GROUP BY CONCAT(YEAR(r.reviewDate), '-', MONTH(r.reviewDate))")
    List<Object[]> findRatingAvgByMonth(@Param("storeId") Long storeId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
