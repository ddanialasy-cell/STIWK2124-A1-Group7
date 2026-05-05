package com.arl.arlbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arl.arlbackend.model.BookCopy;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    Page<BookCopy> findAll(Pageable pageable);

    List<BookCopy> findByBookCopyStatus(String bookCopyStatus);

}
