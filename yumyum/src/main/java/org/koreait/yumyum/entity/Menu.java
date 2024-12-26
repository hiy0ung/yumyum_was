package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MenuCategory menuCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '/images/profile/default1.png'")
    private String imageUrl;

    @Column
    private String menuDescription;

    @Column(nullable = false)
    private int menuPrice;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

    public void addMenuOptionGroup(MenuOptionGroup optionGroup) {
        this.menuOptionGroups.add(optionGroup);
        optionGroup.setMenu(this);
    }
}
