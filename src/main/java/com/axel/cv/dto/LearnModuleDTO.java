package com.axel.cv.dto;

import lombok.Data;

@Data
public class LearnModuleDTO {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String icon;
    private int displayOrder;
    private int lessonCount;
}
