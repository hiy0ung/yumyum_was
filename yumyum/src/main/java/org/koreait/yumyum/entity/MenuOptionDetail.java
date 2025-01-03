package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_option_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MenuOptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String optionDetailName;

    @JoinColumn(name = "menu_option_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuOption menuOption;

    @Column(nullable = false)
    private int additionalFee;
}
