package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MenuCategory menuCategory;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '/images/profile/default1.png'")
    private String imageUrl;

    private String menuDescription;

    @Column(nullable = false)
    private int menuPrice;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isAvailable;


}
