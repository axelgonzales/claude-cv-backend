package com.axel.cv.repository;

import com.axel.cv.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findAllByOrderByDisplayOrderAsc();
    Optional<BlogPost> findBySlug(String slug);
}
