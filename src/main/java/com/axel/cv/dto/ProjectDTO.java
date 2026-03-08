package com.axel.cv.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String longDescription;
    private String technologies;
    private String category;
    private String status;
    private String liveUrl;
    private String githubUrl;
    private String highlights;
    private String imageUrl;
    private int displayOrder;
}
