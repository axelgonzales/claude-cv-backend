package com.axel.cv.service;

import com.axel.cv.dto.SkillGroupDTO;
import com.axel.cv.model.SkillGroup;
import com.axel.cv.repository.SkillGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillGroupService {

    private final SkillGroupRepository skillGroupRepository;

    public List<SkillGroupDTO> getAll() {
        return skillGroupRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDTO).toList();
    }

    public SkillGroupDTO create(SkillGroupDTO dto) {
        return toDTO(skillGroupRepository.save(toEntity(dto)));
    }

    public SkillGroupDTO update(Long id, SkillGroupDTO dto) {
        SkillGroup s = skillGroupRepository.findById(id).orElseThrow();
        s.setCategory(dto.getCategory());
        s.setItems(dto.getItems());
        s.setDisplayOrder(dto.getDisplayOrder());
        return toDTO(skillGroupRepository.save(s));
    }

    public void delete(Long id) {
        skillGroupRepository.deleteById(id);
    }

    private SkillGroupDTO toDTO(SkillGroup s) {
        SkillGroupDTO dto = new SkillGroupDTO();
        dto.setId(s.getId());
        dto.setCategory(s.getCategory());
        dto.setItems(s.getItems());
        dto.setDisplayOrder(s.getDisplayOrder());
        return dto;
    }

    private SkillGroup toEntity(SkillGroupDTO dto) {
        SkillGroup s = new SkillGroup();
        s.setCategory(dto.getCategory());
        s.setItems(dto.getItems());
        s.setDisplayOrder(dto.getDisplayOrder());
        return s;
    }
}
