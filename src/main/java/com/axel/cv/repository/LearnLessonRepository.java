package com.axel.cv.repository;

import com.axel.cv.model.LearnLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnLessonRepository extends JpaRepository<LearnLesson, Long> {
    Optional<LearnLesson> findByModuleSlugAndSlug(String moduleSlug, String lessonSlug);
    boolean existsByModule(com.axel.cv.model.LearnModule module);
}
