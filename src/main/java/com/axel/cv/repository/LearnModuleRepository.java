package com.axel.cv.repository;

import com.axel.cv.model.LearnModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnModuleRepository extends JpaRepository<LearnModule, Long> {
    Optional<LearnModule> findBySlug(String slug);
}
