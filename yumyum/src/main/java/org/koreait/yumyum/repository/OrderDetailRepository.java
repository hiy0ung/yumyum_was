package org.koreait.yumyum.repository;

import org.koreait.yumyum.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = """
        with menu_quantity as (
            select
                o.id as order_id,
                m.id as menu_id,
                m.menu_name,
                m.menu_price,
                count(od.id) as quantity
            from
                orders o
            left outer join order_details od on od.order_id = o.id
            left outer join menus m on m.id = od.menu_id
            group by
                o.id,
                m.id,
                m.menu_name,
                m.menu_price
        )
        select
            o.id as order_id,
            od.id as order_detail_id,
            o.delivery_address,
            o.order_date,
            o.order_state,
            mq.menu_name,
            mq.menu_price,
            mq.quantity,
            mo.option_name as menu_option_name,
            md.option_detail_name as menu_option_detail_name,
            md.additional_fee
        from
            orders o
        left outer join 
            menu_quantity mq on mq.order_id = o.id
        left outer join
            order_details od on od.order_id = o.id and od.menu_id = mq.menu_id
        left outer join
            order_menu_option omo on omo.order_detail_id = od.id
        left outer join
            menu_option_details md on md.id = omo.menu_option_detail_id
        left outer join
            menu_options mo on mo.id = md.menu_option_id
        where
            o.id = :orderId
        group by
            o.id,
            od.id,
            o.delivery_address,
            o.order_date,
            o.order_state,
            mq.menu_name,
            mq.menu_price,
            mq.quantity,
            mo.option_name,
            md.option_detail_name,
            md.additional_fee
        order by
            o.id, od.id;
""", nativeQuery = true)
    List<Object[]> findOrderDetailsWithOptions(@Param("orderId") Long id);
}
