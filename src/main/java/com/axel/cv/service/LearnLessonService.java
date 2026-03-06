package com.axel.cv.service;

import com.axel.cv.dto.LearnLessonDTO;
import com.axel.cv.model.LearnLesson;
import com.axel.cv.repository.LearnLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnLessonService {

    private final LearnLessonRepository repository;

    public List<LearnLessonDTO> getByModule(String moduleSlug) {
        return repository.findByModuleSlugOrderByDisplayOrderAsc(moduleSlug)
                .stream().map(this::toDTO).toList();
    }

    public LearnLessonDTO getBySlug(String moduleSlug, String lessonSlug) {
        return toDTO(repository.findByModuleSlugAndSlug(moduleSlug, lessonSlug).orElseThrow());
    }

    public LearnLessonDTO create(LearnLessonDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public LearnLessonDTO update(Long id, LearnLessonDTO dto) {
        LearnLesson l = repository.findById(id).orElseThrow();
        l.setSlug(dto.getSlug());
        l.setTitle(dto.getTitle());
        l.setExcerpt(dto.getExcerpt());
        l.setContent(dto.getContent());
        l.setModuleSlug(dto.getModuleSlug());
        l.setDisplayOrder(dto.getDisplayOrder());
        l.setReadTime(dto.getReadTime());
        return toDTO(repository.save(l));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private LearnLessonDTO toDTO(LearnLesson l) {
        LearnLessonDTO dto = new LearnLessonDTO();
        dto.setId(l.getId());
        dto.setSlug(l.getSlug());
        dto.setTitle(l.getTitle());
        dto.setExcerpt(l.getExcerpt());
        dto.setContent(l.getContent());
        dto.setModuleSlug(l.getModuleSlug());
        dto.setDisplayOrder(l.getDisplayOrder());
        dto.setReadTime(l.getReadTime());
        return dto;
    }

    private LearnLesson toEntity(LearnLessonDTO dto) {
        LearnLesson l = new LearnLesson();
        l.setSlug(dto.getSlug());
        l.setTitle(dto.getTitle());
        l.setExcerpt(dto.getExcerpt());
        l.setContent(dto.getContent());
        l.setModuleSlug(dto.getModuleSlug());
        l.setDisplayOrder(dto.getDisplayOrder());
        l.setReadTime(dto.getReadTime());
        return l;
    }
}
