package com.axel.cv.repository;

import com.axel.cv.model.LearnModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LearnModuleRepository extends JpaRepository<LearnModule, Long> {
    List<LearnModule> findAllByOrderByDisplayOrderAsc();
    Optional<LearnModule> findBySlug(String slug);
}
