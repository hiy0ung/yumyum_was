package org.koreait.yumyum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "review_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "comment_text", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "comment_date", nullable = false)
    private LocalDateTime commentDate;
}
