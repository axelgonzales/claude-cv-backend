package com.axel.cv.dto;

import lombok.Data;

@Data
public class EducationDTO {
    private Long id;
    private String institution;
    private String degree;
    private String field;
    private String startYear;
    private String endYear;
    private int displayOrder;
}
