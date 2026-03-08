package com.axel.cv.dto;

import java.util.List;

public record LearnLessonDto(
    String slug,
    String title,
    String excerpt,
    String readTime,
    List<String> content
) {}
