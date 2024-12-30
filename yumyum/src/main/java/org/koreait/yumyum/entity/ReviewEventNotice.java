package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="review_event_notices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReviewEventNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "store_id", nullable = false)
    @OneToOne
    private Store store;

    @Column(nullable = false)
    private LocalDateTime noticeDate;

    private String noticePhoto;

    private String noticeText;
}
