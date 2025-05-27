package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = """
        SELECT 
            r.id AS reviewId,
            r.rating,
            r.review_date AS reviewDate,
            r.review_text AS reviewText,
            GROUP_CONCAT(rp.photo_url SEPARATOR ', ') AS reviewPhotoUrls,
            r.is_reported AS isReported,
            g.guest_nickname AS guestNickName,
            g.profile_image AS profileImage,
            o.order_date AS orderDate,
            GROUP_CONCAT(m.menu_name SEPARATOR ', ') AS menuNames,
            rc.comment_text AS reviewComment,
            rc.comment_date AS commentDate
        FROM 
            reviews r
        JOIN
            guests g ON r.guest_id = g.id
        JOIN 
            orders o ON r.order_id = o.id
        LEFT JOIN
            order_details od ON o.id = od.order_id
        LEFT JOIN 
            menus m ON od.menu_id = m.id
        LEFT JOIN
            review_comments rc ON r.id = rc.review_id
        LEFT JOIN 
            review_photos rp ON r.id = rp.review_id
        WHERE
            o.store_id = :storeId
        GROUP BY
             r.id, g.guest_nickname, g.profile_image, o.order_date, rc.comment_text, rc.comment_date, rp.photo_url
""", nativeQuery = true)
    List<Object[]> findReviewsByStoreId(@Param("storeId") Long storeId);
}
