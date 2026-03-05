package com.axel.cv.service;

import com.axel.cv.dto.EducationDTO;
import com.axel.cv.model.Education;
import com.axel.cv.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    public List<EducationDTO> getAll() {
        return educationRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDTO).toList();
    }

    public EducationDTO create(EducationDTO dto) {
        return toDTO(educationRepository.save(toEntity(dto)));
    }

    public EducationDTO update(Long id, EducationDTO dto) {
        Education e = educationRepository.findById(id).orElseThrow();
        e.setInstitution(dto.getInstitution());
        e.setDegree(dto.getDegree());
        e.setField(dto.getField());
        e.setStartYear(dto.getStartYear());
        e.setEndYear(dto.getEndYear());
        e.setDisplayOrder(dto.getDisplayOrder());
        return toDTO(educationRepository.save(e));
    }

    public void delete(Long id) {
        educationRepository.deleteById(id);
    }

    private EducationDTO toDTO(Education e) {
        EducationDTO dto = new EducationDTO();
        dto.setId(e.getId());
        dto.setInstitution(e.getInstitution());
        dto.setDegree(e.getDegree());
        dto.setField(e.getField());
        dto.setStartYear(e.getStartYear());
        dto.setEndYear(e.getEndYear());
        dto.setDisplayOrder(e.getDisplayOrder());
        return dto;
    }

    private Education toEntity(EducationDTO dto) {
        Education e = new Education();
        e.setInstitution(dto.getInstitution());
        e.setDegree(dto.getDegree());
        e.setField(dto.getField());
        e.setStartYear(dto.getStartYear());
        e.setEndYear(dto.getEndYear());
        e.setDisplayOrder(dto.getDisplayOrder());
        return e;
    }
}
