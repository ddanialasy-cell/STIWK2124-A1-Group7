package com.arl.arlbackend.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.arl.arlbackend.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Page<Publisher> findAll(Pageable pageable);

    List<Publisher> findByPublisherNameContaining(String publisherName);

}
