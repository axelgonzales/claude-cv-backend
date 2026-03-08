package com.axel.cv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    @Column(columnDefinition = "TEXT")
    private String technologies;

    private String category;
    private String status;
    private String liveUrl;
    private String githubUrl;

    @Column(columnDefinition = "TEXT")
    private String highlights;

    private String imageUrl;
    private int displayOrder;
}
