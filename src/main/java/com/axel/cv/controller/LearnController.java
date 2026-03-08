package com.axel.cv.controller;

import com.axel.cv.dto.LearnLessonDto;
import com.axel.cv.dto.LearnLessonSummaryDto;
import com.axel.cv.dto.LearnModuleDto;
import com.axel.cv.dto.LearnModuleSummaryDto;
import com.axel.cv.service.LearnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learn")
@RequiredArgsConstructor
public class LearnController {

    private final LearnService learnService;

    @GetMapping("/modules")
    public List<LearnModuleSummaryDto> getAllModules() {
        return learnService.getAllModules();
    }

    @GetMapping("/modules/{slug}")
    public LearnModuleDto getModuleBySlug(@PathVariable String slug) {
        return learnService.getModuleBySlug(slug);
    }

    @GetMapping("/modules/{moduleSlug}/lessons")
    public List<LearnLessonSummaryDto> getLessonsByModule(@PathVariable String moduleSlug) {
        return learnService.getLessonsByModuleSlug(moduleSlug);
    }

    @GetMapping("/modules/{moduleSlug}/lessons/{lessonSlug}")
    public LearnLessonDto getLesson(
        @PathVariable String moduleSlug,
        @PathVariable String lessonSlug
    ) {
        return learnService.getLessonByModuleAndLesson(moduleSlug, lessonSlug);
    }
}
