package com.axel.cv.service;

import com.axel.cv.dto.LearnLessonDto;
import com.axel.cv.dto.LearnModuleDto;
import com.axel.cv.model.LearnLesson;
import com.axel.cv.model.LearnModule;
import com.axel.cv.repository.LearnLessonRepository;
import com.axel.cv.repository.LearnModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnService {

    private final LearnModuleRepository moduleRepository;
    private final LearnLessonRepository lessonRepository;

    public List<LearnModuleDto> getAllModules() {
        return moduleRepository.findAll().stream()
            .map(this::mapToModuleDto)
            .toList();
    }

    public LearnModuleDto getModuleBySlug(String slug) {
        return moduleRepository.findBySlug(slug)
            .map(this::mapToModuleDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found: " + slug));
    }

    public List<LearnLessonDto> getLessonsByModuleSlug(String moduleSlug) {
        return moduleRepository.findBySlug(moduleSlug)
            .map(module -> module.getLessons().stream()
                .map(this::mapToLessonDto)
                .toList())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found: " + moduleSlug));
    }

    public LearnLessonDto getLessonByModuleAndLesson(String moduleSlug, String lessonSlug) {
        return lessonRepository.findByModuleSlugAndSlug(moduleSlug, lessonSlug)
            .map(this::mapToLessonDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found: " + lessonSlug));
    }

    private LearnModuleDto mapToModuleDto(LearnModule module) {
        return new LearnModuleDto(
            module.getSlug(),
            module.getTitle(),
            module.getDescription(),
            module.getIcon(),
            module.getDisplayOrder(),
            module.getLessons().stream()
                .map(this::mapToLessonDto)
                .toList()
        );
    }

    private LearnLessonDto mapToLessonDto(LearnLesson lesson) {
        return new LearnLessonDto(
            lesson.getSlug(),
            lesson.getTitle(),
            lesson.getExcerpt(),
            lesson.getReadTime(),
            lesson.getContent()
        );
    }
}
