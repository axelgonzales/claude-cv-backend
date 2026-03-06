package com.axel.cv.service;

import com.axel.cv.dto.LearnModuleDTO;
import com.axel.cv.model.LearnModule;
import com.axel.cv.repository.LearnModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearnModuleService {

    private final LearnModuleRepository repository;

    public List<LearnModuleDTO> getAll() {
        return repository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDTO).toList();
    }

    public LearnModuleDTO getBySlug(String slug) {
        return toDTO(repository.findBySlug(slug).orElseThrow());
    }

    public LearnModuleDTO create(LearnModuleDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public LearnModuleDTO update(Long id, LearnModuleDTO dto) {
        LearnModule m = repository.findById(id).orElseThrow();
        m.setSlug(dto.getSlug());
        m.setTitle(dto.getTitle());
        m.setDescription(dto.getDescription());
        m.setIcon(dto.getIcon());
        m.setDisplayOrder(dto.getDisplayOrder());
        m.setLessonCount(dto.getLessonCount());
        return toDTO(repository.save(m));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private LearnModuleDTO toDTO(LearnModule m) {
        LearnModuleDTO dto = new LearnModuleDTO();
        dto.setId(m.getId());
        dto.setSlug(m.getSlug());
        dto.setTitle(m.getTitle());
        dto.setDescription(m.getDescription());
        dto.setIcon(m.getIcon());
        dto.setDisplayOrder(m.getDisplayOrder());
        dto.setLessonCount(m.getLessonCount());
        return dto;
    }

    private LearnModule toEntity(LearnModuleDTO dto) {
        LearnModule m = new LearnModule();
        m.setSlug(dto.getSlug());
        m.setTitle(dto.getTitle());
        m.setDescription(dto.getDescription());
        m.setIcon(dto.getIcon());
        m.setDisplayOrder(dto.getDisplayOrder());
        m.setLessonCount(dto.getLessonCount());
        return m;
    }
}
