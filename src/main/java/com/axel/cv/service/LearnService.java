package com.axel.cv.service;

import com.axel.cv.dto.LearnLessonDto;
import com.axel.cv.dto.LearnLessonSummaryDto;
import com.axel.cv.dto.LearnModuleDto;
import com.axel.cv.dto.LearnModuleSummaryDto;
import com.axel.cv.model.LearnLesson;
import com.axel.cv.model.LearnModule;
import com.axel.cv.repository.LearnLessonRepository;
import com.axel.cv.repository.LearnModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnService {

    private final LearnModuleRepository moduleRepository;
    private final LearnLessonRepository lessonRepository;

    /**
     * Returns all modules with their lesson summaries (no lesson content).
     */
    public List<LearnModuleDto> getAllModules() {
        return moduleRepository.findAll().stream()
            .sorted(Comparator.comparing(LearnModule::getDisplayOrder))
            .map(this::mapToModuleDto)
            .toList();
    }

    /**
     * Returns a single module with its lessons listed as summaries (no content field).
     */
    public LearnModuleDto getModuleBySlug(String slug) {
        return moduleRepository.findBySlug(slug)
            .map(this::mapToModuleDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found: " + slug));
    }

    /**
     * Returns lesson summaries (no content) for a given module.
     */
    public List<LearnLessonSummaryDto> getLessonsByModuleSlug(String moduleSlug) {
        return moduleRepository.findBySlug(moduleSlug)
            .map(module -> module.getLessons().stream()
                .map(this::mapToLessonSummaryDto)
                .toList())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found: " + moduleSlug));
    }

    /**
     * Returns the full lesson including its content field.
     */
    public LearnLessonDto getLessonByModuleAndLesson(String moduleSlug, String lessonSlug) {
        return lessonRepository.findByModuleSlugAndSlug(moduleSlug, lessonSlug)
            .map(this::mapToLessonDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found: " + lessonSlug));
    }

    private LearnModuleSummaryDto mapToModuleSummaryDto(LearnModule module) {
        return new LearnModuleSummaryDto(
            module.getSlug(),
            module.getTitle(),
            module.getDescription(),
            module.getIcon(),
            module.getDisplayOrder(),
            module.getLessons().size()
        );
    }

    private LearnModuleDto mapToModuleDto(LearnModule module) {
        return new LearnModuleDto(
            module.getSlug(),
            module.getTitle(),
            module.getDescription(),
            module.getIcon(),
            module.getDisplayOrder(),
            module.getLessons().stream()
                .map(this::mapToLessonSummaryDto)
                .toList()
        );
    }

    private LearnLessonSummaryDto mapToLessonSummaryDto(LearnLesson lesson) {
        return new LearnLessonSummaryDto(
            lesson.getSlug(),
            lesson.getTitle(),
            lesson.getExcerpt(),
            lesson.getReadTime()
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
