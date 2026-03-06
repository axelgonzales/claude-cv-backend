package com.axel.cv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "learn_lessons")
@Data
public class LearnLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String excerpt;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String moduleSlug;

    private int displayOrder;

    private String readTime;
}
