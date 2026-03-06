package com.axel.cv.controller;

import com.axel.cv.dto.LearnLessonDTO;
import com.axel.cv.dto.LearnModuleDTO;
import com.axel.cv.service.LearnLessonService;
import com.axel.cv.service.LearnModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learn")
@RequiredArgsConstructor
public class LearnController {

    private final LearnModuleService moduleService;
    private final LearnLessonService lessonService;

    @GetMapping("/modules")
    public ResponseEntity<List<LearnModuleDTO>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAll());
    }

    @GetMapping("/modules/{slug}")
    public ResponseEntity<LearnModuleDTO> getModule(@PathVariable String slug) {
        return ResponseEntity.ok(moduleService.getBySlug(slug));
    }

    @GetMapping("/modules/{moduleSlug}/lessons")
    public ResponseEntity<List<LearnLessonDTO>> getLessons(@PathVariable String moduleSlug) {
        return ResponseEntity.ok(lessonService.getByModule(moduleSlug));
    }

    @GetMapping("/modules/{moduleSlug}/lessons/{lessonSlug}")
    public ResponseEntity<LearnLessonDTO> getLesson(
            @PathVariable String moduleSlug,
            @PathVariable String lessonSlug) {
        return ResponseEntity.ok(lessonService.getBySlug(moduleSlug, lessonSlug));
    }

    @PostMapping("/modules")
    public ResponseEntity<LearnModuleDTO> createModule(@RequestBody LearnModuleDTO dto) {
        return ResponseEntity.ok(moduleService.create(dto));
    }

    @PutMapping("/modules/{id}")
    public ResponseEntity<LearnModuleDTO> updateModule(@PathVariable Long id, @RequestBody LearnModuleDTO dto) {
        return ResponseEntity.ok(moduleService.update(id, dto));
    }

    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lessons")
    public ResponseEntity<LearnLessonDTO> createLesson(@RequestBody LearnLessonDTO dto) {
        return ResponseEntity.ok(lessonService.create(dto));
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<LearnLessonDTO> updateLesson(@PathVariable Long id, @RequestBody LearnLessonDTO dto) {
        return ResponseEntity.ok(lessonService.update(id, dto));
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
