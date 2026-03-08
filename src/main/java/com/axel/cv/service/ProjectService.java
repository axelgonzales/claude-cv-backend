package com.axel.cv.service;

import com.axel.cv.dto.ProjectDTO;
import com.axel.cv.model.Project;
import com.axel.cv.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<ProjectDTO> getAll() {
        return projectRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDTO).toList();
    }

    public ProjectDTO getBySlug(String slug) {
        return projectRepository.findBySlug(slug)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Project not found: " + slug));
    }

    public ProjectDTO create(ProjectDTO dto) {
        return toDTO(projectRepository.save(toEntity(dto)));
    }

    public ProjectDTO update(Long id, ProjectDTO dto) {
        Project e = projectRepository.findById(id).orElseThrow();
        e.setSlug(dto.getSlug());
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setLongDescription(dto.getLongDescription());
        e.setTechnologies(dto.getTechnologies());
        e.setCategory(dto.getCategory());
        e.setStatus(dto.getStatus());
        e.setLiveUrl(dto.getLiveUrl());
        e.setGithubUrl(dto.getGithubUrl());
        e.setHighlights(dto.getHighlights());
        e.setImageUrl(dto.getImageUrl());
        e.setDisplayOrder(dto.getDisplayOrder());
        return toDTO(projectRepository.save(e));
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDTO toDTO(Project e) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(e.getId());
        dto.setSlug(e.getSlug());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setLongDescription(e.getLongDescription());
        dto.setTechnologies(e.getTechnologies());
        dto.setCategory(e.getCategory());
        dto.setStatus(e.getStatus());
        dto.setLiveUrl(e.getLiveUrl());
        dto.setGithubUrl(e.getGithubUrl());
        dto.setHighlights(e.getHighlights());
        dto.setImageUrl(e.getImageUrl());
        dto.setDisplayOrder(e.getDisplayOrder());
        return dto;
    }

    private Project toEntity(ProjectDTO dto) {
        Project e = new Project();
        e.setSlug(dto.getSlug());
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setLongDescription(dto.getLongDescription());
        e.setTechnologies(dto.getTechnologies());
        e.setCategory(dto.getCategory());
        e.setStatus(dto.getStatus());
        e.setLiveUrl(dto.getLiveUrl());
        e.setGithubUrl(dto.getGithubUrl());
        e.setHighlights(dto.getHighlights());
        e.setImageUrl(dto.getImageUrl());
        e.setDisplayOrder(dto.getDisplayOrder());
        return e;
    }
}
