package com.axel.cv.repository;

import com.axel.cv.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllByOrderByDisplayOrderAsc();
}
