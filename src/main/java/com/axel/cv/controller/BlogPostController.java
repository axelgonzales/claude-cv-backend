package com.axel.cv.controller;

import com.axel.cv.dto.BlogPostDTO;
import com.axel.cv.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping
    public ResponseEntity<List<BlogPostDTO>> getAll() {
        return ResponseEntity.ok(blogPostService.getAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BlogPostDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(blogPostService.getBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<BlogPostDTO> create(@RequestBody BlogPostDTO dto) {
        return ResponseEntity.ok(blogPostService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDTO> update(@PathVariable Long id, @RequestBody BlogPostDTO dto) {
        return ResponseEntity.ok(blogPostService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blogPostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
