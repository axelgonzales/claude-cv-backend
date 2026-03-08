package com.axel.cv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "blog_posts")
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String excerpt;

    private String category;
    private String date;
    private String readTime;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String tags;

    private String imageUrl;
    private int displayOrder;
}
