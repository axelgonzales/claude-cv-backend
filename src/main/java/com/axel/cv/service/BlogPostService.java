package com.axel.cv.service;

import com.axel.cv.dto.BlogPostDTO;
import com.axel.cv.model.BlogPost;
import com.axel.cv.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public List<BlogPostDTO> getAll() {
        return blogPostRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDTO).toList();
    }

    public BlogPostDTO getBySlug(String slug) {
        return blogPostRepository.findBySlug(slug)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Blog post not found: " + slug));
    }

    public BlogPostDTO create(BlogPostDTO dto) {
        return toDTO(blogPostRepository.save(toEntity(dto)));
    }

    public BlogPostDTO update(Long id, BlogPostDTO dto) {
        BlogPost e = blogPostRepository.findById(id).orElseThrow();
        e.setSlug(dto.getSlug());
        e.setTitle(dto.getTitle());
        e.setExcerpt(dto.getExcerpt());
        e.setCategory(dto.getCategory());
        e.setDate(dto.getDate());
        e.setReadTime(dto.getReadTime());
        e.setContent(dto.getContent());
        e.setTags(dto.getTags());
        e.setImageUrl(dto.getImageUrl());
        e.setDisplayOrder(dto.getDisplayOrder());
        return toDTO(blogPostRepository.save(e));
    }

    public void delete(Long id) {
        blogPostRepository.deleteById(id);
    }

    private BlogPostDTO toDTO(BlogPost e) {
        BlogPostDTO dto = new BlogPostDTO();
        dto.setId(e.getId());
        dto.setSlug(e.getSlug());
        dto.setTitle(e.getTitle());
        dto.setExcerpt(e.getExcerpt());
        dto.setCategory(e.getCategory());
        dto.setDate(e.getDate());
        dto.setReadTime(e.getReadTime());
        dto.setContent(e.getContent());
        dto.setTags(e.getTags());
        dto.setImageUrl(e.getImageUrl());
        dto.setDisplayOrder(e.getDisplayOrder());
        return dto;
    }

    private BlogPost toEntity(BlogPostDTO dto) {
        BlogPost e = new BlogPost();
        e.setSlug(dto.getSlug());
        e.setTitle(dto.getTitle());
        e.setExcerpt(dto.getExcerpt());
        e.setCategory(dto.getCategory());
        e.setDate(dto.getDate());
        e.setReadTime(dto.getReadTime());
        e.setContent(dto.getContent());
        e.setTags(dto.getTags());
        e.setImageUrl(dto.getImageUrl());
        e.setDisplayOrder(dto.getDisplayOrder());
        return e;
    }
}
