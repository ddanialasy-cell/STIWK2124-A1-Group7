package com.arl.arlbackend.service;

import com.arl.arlbackend.exception.ResourceNotFoundException;
import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // 1. CREATE
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    //2. All
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // 3. GET BY ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    // 4. UPDATE
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());
        book.setDescription(bookDetails.getDescription());
        return bookRepository.save(book);
    }

    // 5. DELETE
    public void deleteBook(Long id) {
        Book book = getBookById(id); 
        bookRepository.delete(book);
    }

    // 6. GET PAGINATED
    public Page<Book> getBooksPaginated(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    //SEARCH BOOK
    public Page<Book> searchBooks(String q, Pageable pageable) {
    return bookRepository.findByTitleContainingIgnoreCase(q, pageable);
}
}