package com.axel.cv.dto;

public record LearnModuleSummaryDto(
    String slug,
    String title,
    String description,
    String icon,
    Integer displayOrder,
    int lessonCount
) {}
