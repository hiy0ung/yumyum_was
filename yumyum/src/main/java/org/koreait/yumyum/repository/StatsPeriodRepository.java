package org.koreait.yumyum.repository;


import org.koreait.yumyum.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsPeriodRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT DATE(orderDate) AS orderDay, " +
            "SUM(totalPrice) AS dailyPrice " +
            "FROM Order " +
            "WHERE YEAR(orderDate) = :orderDateYear AND MONTH(orderDate) = :orderDateMonth " +
            "GROUP BY DATE(orderDate), orderDate " +
            "ORDER BY orderDate ")
    List<Object[]> findDailySales(@Param("orderDateYear") int year,
                                  @Param("orderDateMonth") int month
    );

    @Query("SELECT YEAR (orderDate) AS orderYear, " +
            "MONTH (orderDate) AS orderMonth, " +
            "SUM(totalPrice) AS monthPrice " +
            "FROM Order " +
            "WHERE YEAR (orderDate) = :orderDateYear AND MONTH (orderDate) = :orderDateMonth " +
            "GROUP BY YEAR (orderDate), MONTH (orderDate)"
    )
    List<Object[]> findMonthSales(@Param("orderDateYear") int year,
                                  @Param("orderDateMonth") int month
    );

    @Query("SELECT YEAR (orderDate) AS orderYear, " +
            "SUM (totalPrice) AS yearPrice " +
            "FROM Order " +
            "WHERE YEAR (orderDate) = :orderDateYear " +
            "GROUP BY YEAR (orderDate) " +
            "ORDER BY YEAR (orderDate) "
    )
    List<Object[]> findYearSales(@Param("orderDateYear") int year);
}