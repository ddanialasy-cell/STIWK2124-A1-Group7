package com.arl.arlbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long bookID) {
        return bookService.getBookById(bookID);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long bookID) {
        bookService.deleteBook(bookID);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long bookID, @Valid @RequestBody Book bookDetails) {
        return bookService.updateBook(bookID, bookDetails);
    }

    @GetMapping("/page")
    public Page<Book> getBooksWithPagination(
            @PageableDefault(size = 5) Pageable pageable) {
        return bookService.getBooks(pageable);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String bookTitle) {
        return bookService.searchBooks(bookTitle);
    }

    /*   
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long bookID) {
        Book book = bookService.getBookById(bookID);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long bookID, @Valid @RequestBody Book bookDetails) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long bookID) {
        bookService.deleteBook(bookID);
        return ResponseEntity.noContent().build();
    }*/

}
