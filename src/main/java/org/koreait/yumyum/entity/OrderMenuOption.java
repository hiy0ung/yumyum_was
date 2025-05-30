package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_menu_option")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_detail_id", nullable = false)
    private MenuOptionDetail menuOptionDetail;
}
