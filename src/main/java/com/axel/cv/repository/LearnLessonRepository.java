package com.axel.cv.repository;

import com.axel.cv.model.LearnLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LearnLessonRepository extends JpaRepository<LearnLesson, Long> {
    List<LearnLesson> findByModuleSlugOrderByDisplayOrderAsc(String moduleSlug);
    Optional<LearnLesson> findByModuleSlugAndSlug(String moduleSlug, String slug);
}
