package com.arl.arlbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arl.arlbackend.model.Author;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Page<Author> findAll(Pageable pageable);

    List<Author> findByAuthorName(String authorName);

}
