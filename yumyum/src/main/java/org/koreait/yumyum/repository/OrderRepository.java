package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderState(String orderState);

    // 통계 - 시간 별 매출 합계
    @Query("SELECT DATE(o.orderDate) AS date, " +
            "HOUR (o.orderDate) AS hour, " +
            "SUM(o.totalPrice) AS revenue " +
            "FROM Order AS o " +
            "WHERE YEAR(o.orderDate) = :orderDateYear " +
            "AND MONTH(o.orderDate) = :orderDateMonth " +
            "AND DAY(o.orderDate) = :orderDateDay " +
            "AND o.orderState = '2' " +
            "GROUP BY DATE(o.orderDate), HOUR (o.orderDate) " +
            "ORDER BY DATE(o.orderDate), HOUR (o.orderDate)"
    )
    List<Object[]> findRevenueByOrderDate(
            // param 뒤에 "" 안orderDateMonth 값은 위의 쿼리문에서 쓰이는 이름!!
            @Param("orderDateYear") int year,
            @Param("orderDateMonth") int month,
            @Param("orderDateDay") int day
    );
}
