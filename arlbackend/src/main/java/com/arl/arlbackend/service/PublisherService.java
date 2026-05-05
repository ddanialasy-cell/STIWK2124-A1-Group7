package com.arl.arlbackend.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.arl.arlbackend.exception.ResourceNotFoundException;
import com.arl.arlbackend.model.Publisher;
import com.arl.arlbackend.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.repository.BookRepository;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher savePublisherForBook(Long bookId, Publisher publisher) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        publisher.getBook().add(book);
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id " + id));
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    public Publisher updatePublisher(Long id, Publisher publisherDetails) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);

        if (publisher != null) {
            publisher.setPublisherName(publisherDetails.getPublisherName());
            publisher.setPublisherContact(publisherDetails.getPublisherContact());

            return publisherRepository.save(publisher);
        }

        return null;
    }

    public Page<Publisher> getPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public List<Publisher> searchPublishers(String publisherName) {
        return publisherRepository.findByPublisherNameContaining(publisherName);
    }    

}
