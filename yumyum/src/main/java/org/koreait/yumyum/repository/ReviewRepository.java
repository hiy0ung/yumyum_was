package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = """
    WITH MenuInfo AS (
        SELECT
            od.order_id,
            GROUP_CONCAT(DISTINCT m.menu_name ORDER BY m.menu_name ASC) AS menu_names
        FROM order_details od
        JOIN menus m ON od.menu_id = m.id
        GROUP BY od.order_id
    )
    SELECT
        r.id AS review_id,                     -- 리뷰 ID
        r.rating,                              -- 리뷰 별점
        r.review_date,                         -- 리뷰 작성 날짜
        r.review_text,                         -- 리뷰 내용
        r.is_reported,                         -- 리뷰 신고 여부
        g.guest_nickname,                      -- 게스트 닉네임
        g.profile_image,                       -- 게스트 프로필 이미지
        o.order_date,                          -- 주문 날짜
        mi.menu_names,                         -- 여러 메뉴 이름
        rc.comment AS review_comment,          -- 리뷰에 대한 답글(댓글)
        rc.comment_date AS comment_date        -- 답글 작성 날짜
    FROM
        reviews r
    JOIN orders o ON r.order_id = o.id                         -- 리뷰와 주문 조인
    JOIN guests g ON o.id = g.order_id                         -- 주문과 게스트 조인
    LEFT JOIN review_comments rc ON r.id = rc.review_id        -- 리뷰와 답글 조인 (LEFT JOIN: 답글 없을 수도 있음)
    LEFT JOIN MenuInfo mi ON o.id = mi.order_id                -- 주문과 메뉴 정보 조인
    WHERE o.store_id = storeId
    ORDER BY
        r.review_date DESC;
    """, nativeQuery = true)
    List<Object[]> findReviewsByStoreId(Long storeId);
}
