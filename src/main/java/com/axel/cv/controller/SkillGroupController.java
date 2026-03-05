package com.axel.cv.controller;

import com.axel.cv.dto.SkillGroupDTO;
import com.axel.cv.service.SkillGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillGroupController {

    private final SkillGroupService skillGroupService;

    @GetMapping
    public ResponseEntity<List<SkillGroupDTO>> getAll() {
        return ResponseEntity.ok(skillGroupService.getAll());
    }

    @PostMapping
    public ResponseEntity<SkillGroupDTO> create(@RequestBody SkillGroupDTO dto) {
        return ResponseEntity.ok(skillGroupService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillGroupDTO> update(@PathVariable Long id, @RequestBody SkillGroupDTO dto) {
        return ResponseEntity.ok(skillGroupService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
