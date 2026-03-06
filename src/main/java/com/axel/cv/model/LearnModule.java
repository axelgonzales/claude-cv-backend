package com.axel.cv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "learn_modules")
@Data
public class LearnModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    private String icon;

    private int displayOrder;

    private int lessonCount;
}
