package com.axel.cv.dto;

import lombok.Data;

@Data
public class SkillGroupDTO {
    private Long id;
    private String category;
    private String items;
    private int displayOrder;
}
