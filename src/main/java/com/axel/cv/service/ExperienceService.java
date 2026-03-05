package com.axel.cv.service;

import com.axel.cv.dto.ExperienceDTO;
import com.axel.cv.model.Experience;
import com.axel.cv.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public List<ExperienceDTO> getAll() {
        return experienceRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDTO).toList();
    }

    public ExperienceDTO create(ExperienceDTO dto) {
        return toDTO(experienceRepository.save(toEntity(dto)));
    }

    public ExperienceDTO update(Long id, ExperienceDTO dto) {
        Experience e = experienceRepository.findById(id).orElseThrow();
        e.setCompany(dto.getCompany());
        e.setRole(dto.getRole());
        e.setStartDate(dto.getStartDate());
        e.setEndDate(dto.getEndDate());
        e.setCurrent(dto.isCurrent());
        e.setDescription(dto.getDescription());
        e.setTechnologies(dto.getTechnologies());
        e.setDisplayOrder(dto.getDisplayOrder());
        return toDTO(experienceRepository.save(e));
    }

    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }

    private ExperienceDTO toDTO(Experience e) {
        ExperienceDTO dto = new ExperienceDTO();
        dto.setId(e.getId());
        dto.setCompany(e.getCompany());
        dto.setRole(e.getRole());
        dto.setStartDate(e.getStartDate());
        dto.setEndDate(e.getEndDate());
        dto.setCurrent(e.isCurrent());
        dto.setDescription(e.getDescription());
        dto.setTechnologies(e.getTechnologies());
        dto.setDisplayOrder(e.getDisplayOrder());
        return dto;
    }

    private Experience toEntity(ExperienceDTO dto) {
        Experience e = new Experience();
        e.setCompany(dto.getCompany());
        e.setRole(dto.getRole());
        e.setStartDate(dto.getStartDate());
        e.setEndDate(dto.getEndDate());
        e.setCurrent(dto.isCurrent());
        e.setDescription(dto.getDescription());
        e.setTechnologies(dto.getTechnologies());
        e.setDisplayOrder(dto.getDisplayOrder());
        return e;
    }
}
