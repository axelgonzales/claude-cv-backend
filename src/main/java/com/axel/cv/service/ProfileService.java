package com.axel.cv.service;

import com.axel.cv.dto.ProfileDTO;
import com.axel.cv.model.Profile;
import com.axel.cv.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDTO getProfile() {
        return profileRepository.findAll().stream()
                .findFirst()
                .map(this::toDTO)
                .orElse(null);
    }

    public ProfileDTO updateProfile(ProfileDTO dto) {
        Profile profile = profileRepository.findAll().stream()
                .findFirst()
                .orElse(new Profile());
        profile.setName(dto.getName());
        profile.setTitle(dto.getTitle());
        profile.setSummary(dto.getSummary());
        profile.setEmail(dto.getEmail());
        profile.setPhone(dto.getPhone());
        profile.setLinkedin(dto.getLinkedin());
        profile.setGithub(dto.getGithub());
        profile.setLocation(dto.getLocation());
        return toDTO(profileRepository.save(profile));
    }

    private ProfileDTO toDTO(Profile p) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setTitle(p.getTitle());
        dto.setSummary(p.getSummary());
        dto.setEmail(p.getEmail());
        dto.setPhone(p.getPhone());
        dto.setLinkedin(p.getLinkedin());
        dto.setGithub(p.getGithub());
        dto.setLocation(p.getLocation());
        return dto;
    }
}
