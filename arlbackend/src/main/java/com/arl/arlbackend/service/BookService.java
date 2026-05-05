package com.arl.arlbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arl.arlbackend.exception.ResourceNotFoundException;
import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.arl.arlbackend.model.Publisher;
import com.arl.arlbackend.repository.PublisherRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public Book saveBook(Book book) {
        Publisher publisher;

        if (book.getPublisher() == null || book.getPublisher().getPublisherID() == null) {
            
            Long defaultPublisherID = 1L;
            publisher = publisherRepository.findById(defaultPublisherID)
                    .orElseThrow(() -> new ResourceNotFoundException("Default publisher not found with id " + defaultPublisherID));

            book.setPublisher(publisher);
        }

        else {

            Long publisherID = book.getPublisher().getPublisherID();
            publisher = publisherRepository.findById(publisherID)
                    .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id " + publisherID));

            book.setPublisher(publisher);
        }
        
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    public void deleteBook(Long id) {
        getBookById(id);
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book bookDetails) {
        //Book book = bookRepository.findById(id).orElse(null);

        Book book = getBookById(id);

        if (book != null) {
            book.setBookTitle(bookDetails.getBookTitle());
            book.setBookDescription(bookDetails.getBookDescription());
            book.setBookCategory(bookDetails.getBookCategory());
            book.setBookGenre(bookDetails.getBookGenre());
            book.setBookPublishDate(bookDetails.getBookPublishDate());

            return bookRepository.save(book);
        }

        return null;
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public List<Book> searchBooks(String bookTitle) {
        return bookRepository.findByBookTitleContaining(bookTitle);
    }

}
