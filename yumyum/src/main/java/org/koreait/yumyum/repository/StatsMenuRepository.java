package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsMenuRepository extends JpaRepository<Order, Long> {
    @Query(value = """
        WITH menu_quantity AS (
            SELECT
                s.owner_id AS user_id,
                o.id AS order_id,
                m.id AS menu_id,
                m.menu_name,
                m.menu_price,
                COUNT(od.id) AS quantity
            FROM
                orders o
            INNER JOIN order_details od ON od.order_id = o.id
            INNER JOIN menus m ON m.id = od.menu_id
            INNER JOIN stores s ON s.id = m.store_id
            WHERE
                s.owner_id = :userId
                AND YEAR(o.order_date) = year(now())
                AND MONTH(o.order_date) = month(now())
                AND DAY(o.order_date) = day(now())
                AND o.order_state = '2'
            GROUP BY
                s.owner_id, o.id, m.id, m.menu_name, m.menu_price
        ),
        menu_option_fees AS (
            SELECT
                od.order_id,
                od.menu_id,
                SUM(md.additional_fee) AS total_additional_fee
            FROM
                order_details od
            INNER JOIN order_menu_option omo ON omo.order_detail_id = od.id
            INNER JOIN menu_option_details md ON md.id = omo.menu_option_detail_id
            GROUP BY
                od.order_id, od.menu_id
        )
        SELECT
            date(o.order_date),
            mq.menu_name,
            mq.quantity,
            (mq.menu_price * mq.quantity + COALESCE(mof.total_additional_fee, 0)) AS sum_total_price
        FROM
            orders o
        INNER JOIN
                menu_quantity mq ON mq.order_id = o.id
        INNER JOIN
                menu_option_fees mof ON mof.order_id = o.id AND mof.menu_id = mq.menu_id
        GROUP BY
            date(o.order_date), mq.menu_name, mq.quantity, mq.menu_price, mof.total_additional_fee
""", nativeQuery = true)
    List<Object[]> findTodayTotalPriceAndQuantityByOrderDate(@Param("userId") Long id);

    @Query(value = """
        WITH menu_quantity AS (
            SELECT
                s.owner_id AS user_id,
                o.id AS order_id,
                m.id AS menu_id,
                m.menu_name,
                m.menu_price,
                COUNT(od.id) AS quantity
            FROM
                orders o
            INNER JOIN order_details od ON od.order_id = o.id
            INNER JOIN menus m ON m.id = od.menu_id
            INNER JOIN stores s ON s.id = m.store_id
            WHERE
                s.owner_id = :userId
                AND YEAR(o.order_date) = :year
                AND MONTH(o.order_date) = :month
                AND DAY(o.order_date) = :day
                AND o.order_state = '2'
            GROUP BY
                s.owner_id, o.id, m.id, m.menu_name, m.menu_price
        ),
        menu_option_fees AS (
            SELECT
                od.order_id,
                od.menu_id,
                SUM(md.additional_fee) AS total_additional_fee
            FROM
                order_details od
            INNER JOIN order_menu_option omo ON omo.order_detail_id = od.id
            INNER JOIN menu_option_details md ON md.id = omo.menu_option_detail_id
            GROUP BY
                od.order_id, od.menu_id
        )
        SELECT
            date(o.order_date),
            mq.menu_name,
            mq.quantity,
            (mq.menu_price * mq.quantity + COALESCE(mof.total_additional_fee, 0)) AS sum_total_price
        FROM
            orders o
        INNER JOIN
                menu_quantity mq ON mq.order_id = o.id
        INNER JOIN
                menu_option_fees mof ON mof.order_id = o.id AND mof.menu_id = mq.menu_id
        GROUP BY
            date(o.order_date), mq.menu_name, mq.quantity, mq.menu_price, mof.total_additional_fee
""", nativeQuery = true)
    List<Object[]> findDayTotalPriceAndQuantityByOrderDate(@Param("year") int year,
                                                           @Param("month") int month,
                                                           @Param("day") int day,
                                                           @Param("userId") Long id);

    @Query(value = """
        WITH menu_quantity AS (
            SELECT
                s.owner_id AS user_id,
                o.id AS order_id,
                m.id AS menu_id,
                m.menu_name,
                m.menu_price,
                COUNT(od.id) AS quantity
            FROM
                orders o
            INNER JOIN order_details od ON od.order_id = o.id
            INNER JOIN menus m ON m.id = od.menu_id
            INNER JOIN stores s ON s.id = m.store_id
            WHERE
                s.owner_id = :userId
                AND YEAR(o.order_date) = :year
                AND MONTH(o.order_date) = :month
                AND o.order_state = '2'
            GROUP BY
                s.owner_id, o.id, m.id, m.menu_name, m.menu_price
        ),
        menu_option_fees AS (
            SELECT
                od.order_id,
                od.menu_id,
                SUM(md.additional_fee) AS total_additional_fee
            FROM
                order_details od
            INNER JOIN order_menu_option omo ON omo.order_detail_id = od.id
            INNER JOIN menu_option_details md ON md.id = omo.menu_option_detail_id
            GROUP BY
                od.order_id, od.menu_id
        )
        SELECT
            date(o.order_date),
            mq.menu_name,
            mq.quantity,
            (mq.menu_price * mq.quantity + COALESCE(mof.total_additional_fee, 0)) AS sum_total_price
        FROM
            orders o
        INNER JOIN
                menu_quantity mq ON mq.order_id = o.id
        INNER JOIN
                menu_option_fees mof ON mof.order_id = o.id AND mof.menu_id = mq.menu_id
        GROUP BY
            date(o.order_date), mq.menu_name, mq.quantity, mq.menu_price, mof.total_additional_fee
""", nativeQuery = true)
    List<Object[]> findMonthTotalPriceAndQuantityByOrderDate(@Param("year") int year,
                                                             @Param("month") int month,
                                                             @Param("userId") Long id);
}
