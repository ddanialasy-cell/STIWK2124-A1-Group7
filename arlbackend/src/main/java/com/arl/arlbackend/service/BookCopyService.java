package com.arl.arlbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.arl.arlbackend.exception.ResourceNotFoundException;
import com.arl.arlbackend.model.BookCopy;
import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.repository.BookCopyRepository;
import com.arl.arlbackend.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy saveBookCopyForBook(Long bookId, BookCopy bookCopy) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        bookCopy.setBook(book);
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy getBookCopyById(Long id) {
        return bookCopyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book copy not found with id " + id));
    }

    public void deleteBookCopy(Long id) {
        bookCopyRepository.deleteById(id);
    }

    public BookCopy updateBookCopy(Long id, BookCopy bookCopyDetails) {
        BookCopy bookCopy = bookCopyRepository.findById(id).orElse(null);

        if (bookCopy != null) {
            bookCopy.setBookCopyStatus(bookCopyDetails.getBookCopyStatus());

            return bookCopyRepository.save(bookCopy);
        }

        return null;
    }

    public Page<BookCopy> getBookCopies(Pageable pageable) {
        return bookCopyRepository.findAll(pageable);
    }

    public List<BookCopy> searchBookCopies(String bookCopyStatus) {
        return bookCopyRepository.findByBookCopyStatus(bookCopyStatus);
    }

}
