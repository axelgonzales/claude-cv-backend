package com.axel.cv.dto;

import java.util.List;

public record LearnModuleDto(
    String slug,
    String title,
    String description,
    String icon,
    Integer displayOrder,
    List<LearnLessonDto> lessons
) {}
