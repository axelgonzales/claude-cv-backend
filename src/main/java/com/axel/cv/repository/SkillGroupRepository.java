package com.axel.cv.repository;

import com.axel.cv.model.SkillGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SkillGroupRepository extends JpaRepository<SkillGroup, Long> {
    List<SkillGroup> findAllByOrderByDisplayOrderAsc();
}
