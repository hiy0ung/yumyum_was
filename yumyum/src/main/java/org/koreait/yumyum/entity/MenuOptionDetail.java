package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_option_details")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String optionDetailName;

    @JoinColumn(name = "option_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuOption menuOption;

//    @JoinColumn(name = "order_detail_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private OrderDetail orderDetail;

    @Column
    private String additionalFee;
}
