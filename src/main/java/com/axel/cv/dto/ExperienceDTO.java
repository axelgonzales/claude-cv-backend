package com.axel.cv.dto;

import lombok.Data;

@Data
public class ExperienceDTO {
    private Long id;
    private String company;
    private String role;
    private String startDate;
    private String endDate;
    private boolean isCurrent;
    private String description;
    private String technologies;
    private int displayOrder;
}
