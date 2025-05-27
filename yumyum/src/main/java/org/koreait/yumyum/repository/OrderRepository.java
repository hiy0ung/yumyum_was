package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
        with menu_total_price as (
            select
                o.id as order_id,
                sum(m.menu_price) as menu_total_price
            from
                orders o
                left outer join order_details od on od.order_id = o.id
                left outer join menus m on m.id = od.menu_id
            group by
                o.id
        ),
        option_total_price as (
            select
                o.id as order_id,
                sum(md.additional_fee) as option_total_price
            from
                orders o
            left outer join
                order_details od on od.order_id = o.id
            left outer join
                menus m on m.id = od.menu_id
            left outer join
                order_menu_option omo on omo.order_detail_id = od.id
            left outer join
                menu_option_details md on md.id = omo.menu_option_detail_id
            group by
                o.id
        )
        select
            o.id as order_id,
            o.store_id,
            o.delivery_address,
            o.order_date,
            g.guest_nickname,
            o.order_state,
            CAST(COALESCE(mtp.menu_total_price, 0) + COALESCE(otp.option_total_price, 0) AS DECIMAL(10,2)) as sum_total_price
        from
            orders o
        left outer join
            guests g on g.order_id = o.id
        left outer join
            menu_total_price mtp on mtp.order_id = o.id
        left outer join
            option_total_price otp on otp.order_id = o.id
        left outer join
            order_details od on od.order_id = o.id
        left outer join
            menus m on m.id = od.menu_id
        left outer join
            order_menu_option omo on omo.order_detail_id = od.id
        left outer join
            menu_option_details md on md.id = omo.menu_option_detail_id
        where
            o.store_id = :storeId
        group by
            o.id,
            o.store_id,
            o.delivery_address,
            g.guest_nickname,
            o.order_state
        order by
            o.id desc;
""", nativeQuery = true)
    List<Object[]> findAllOrderWithTotalPrice(@Param("storeId") Long id);

    @Query(value = """
        with menu_total_price as (
            select
                o.id as order_id,
                sum(m.menu_price) as menu_total_price
            from
                orders o
                left join order_details od on od.order_id = o.id
                left join menus m on m.id = od.menu_id
            where o.id = :orderId
            group by o.id
        ),
        option_total_price as (
            select
                o.id as order_id,
                sum(md.additional_fee) as option_total_price
            from
                orders o
                left join order_details od on od.order_id = o.id
                left join order_menu_option omo on omo.order_detail_id = od.id
                left join menu_option_details md on md.id = omo.menu_option_detail_id
            where o.id = :orderId
            group by o.id
        )
        select
            o.id as order_id,
            o.store_id,
            o.delivery_address,
            o.order_date,
            g.guest_nickname,
            o.order_state,
            coalesce(mtp.menu_total_price, 0) + coalesce(otp.option_total_price, 0) as sum_total_price
        from
            orders o
            left join guests g on g.order_id = o.id
            left join menu_total_price mtp on mtp.order_id = o.id
            left join option_total_price otp on otp.order_id = o.id
        where o.id = :orderId
        and o.store_id = :storeId
        group by
            o.id, o.store_id, o.delivery_address, g.guest_nickname, o.order_state
""", nativeQuery = true)
    List<Object[]> findOrderWithTotalPriceById(@Param("orderId") Long orderId, @Param("storeId") Long storeId);
}
