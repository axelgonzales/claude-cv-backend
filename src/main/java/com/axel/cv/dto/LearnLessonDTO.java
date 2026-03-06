package com.axel.cv.dto;

import lombok.Data;

@Data
public class LearnLessonDTO {
    private Long id;
    private String slug;
    private String title;
    private String excerpt;
    private String content;
    private String moduleSlug;
    private int displayOrder;
    private String readTime;
}
