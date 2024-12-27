package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r.rating, COUNT(r.id) AS reviewCount " +
            "FROM Review r " +
            "JOIN Order o ON r.order.id = o.id " +
            "WHERE o.store.id = :storeId " +
            "GROUP BY r.rating")
    List<Object[]> findReviewCountByRating(@Param("storeId") Long storeId);


    @Query("SELECT MONTH(r.reviewDate) as reviewMonth, CAST(ROUND(avg(r.rating)) AS INTEGER) avgRating " +
            "FROM Review r " +
            "JOIN Order o ON r.order.id = o.id " +
            "WHERE o.store.id = :storeId " +
            "AND MONTH(r.reviewDate) between :startDate and :endDate " +
            "GROUP BY reviewMonth")
    List<Object[]> findRatingAvgByMonth(@Param("storeId") Long storeId, @Param("startDate") int startDate, @Param("endDate") int endDate);
}
