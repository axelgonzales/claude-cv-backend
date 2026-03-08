package com.axel.cv.dto;

import lombok.Data;

@Data
public class BlogPostDTO {
    private Long id;
    private String slug;
    private String title;
    private String excerpt;
    private String category;
    private String date;
    private String readTime;
    private String content;
    private String tags;
    private String imageUrl;
    private int displayOrder;
}
