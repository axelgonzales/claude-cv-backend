package com.axel.cv.controller;

import com.axel.cv.dto.EducationDTO;
import com.axel.cv.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAll() {
        return ResponseEntity.ok(educationService.getAll());
    }

    @PostMapping
    public ResponseEntity<EducationDTO> create(@RequestBody EducationDTO dto) {
        return ResponseEntity.ok(educationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationDTO> update(@PathVariable Long id, @RequestBody EducationDTO dto) {
        return ResponseEntity.ok(educationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        educationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
