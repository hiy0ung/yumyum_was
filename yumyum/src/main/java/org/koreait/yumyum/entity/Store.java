package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "stores")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="owner_id", nullable = false)
    @OneToOne
    private User user;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalTime openingTime;

    @Column(nullable = false)
    private LocalTime closingTime;

    @Column
    private LocalTime breakStartTime;

    @Column
    private LocalTime breakEndTime;

    @Column
    private String address;

    @Column(columnDefinition = "TEXT")
    private String description;
}

