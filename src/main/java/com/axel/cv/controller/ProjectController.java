package com.axel.cv.controller;

import com.axel.cv.dto.ProjectDTO;
import com.axel.cv.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProjectDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(projectService.getBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
