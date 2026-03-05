package com.axel.cv.controller;

import com.axel.cv.dto.ExperienceDTO;
import com.axel.cv.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<List<ExperienceDTO>> getAll() {
        return ResponseEntity.ok(experienceService.getAll());
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> create(@RequestBody ExperienceDTO dto) {
        return ResponseEntity.ok(experienceService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDTO> update(@PathVariable Long id, @RequestBody ExperienceDTO dto) {
        return ResponseEntity.ok(experienceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
