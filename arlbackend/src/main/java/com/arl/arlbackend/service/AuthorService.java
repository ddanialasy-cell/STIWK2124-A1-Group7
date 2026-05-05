package com.arl.arlbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arl.arlbackend.exception.ResourceNotFoundException;
import com.arl.arlbackend.model.Author;
import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.arl.arlbackend.repository.BookRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author saveAuthorForBook(Long bookId, Author author) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
                
        author.getBook().add(book);
        book.getAuthor().add(author);
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id).orElse(null);

        if (author != null) {
            author.setAuthorName(authorDetails.getAuthorName());
            author.setAuthorBiography(authorDetails.getAuthorBiography());
            author.setAuthorNationality(authorDetails.getAuthorNationality());

            return authorRepository.save(author);
        }

        return null;
    }

    public Page<Author> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public List<Author> searchAuthors(String authorName) {
        return authorRepository.findByAuthorName(authorName);
    }

}
