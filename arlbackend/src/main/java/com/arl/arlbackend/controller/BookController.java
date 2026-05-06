package com.arl.arlbackend.controller;

import com.arl.arlbackend.model.Book;
import com.arl.arlbackend.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 1. POST — Create Book
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book saved = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

<<<<<<< Updated upstream
    // 2. GET — Get All Books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // 3. GET — Get Book by ID
=======
    // GET ALL BOOKS
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET PAGINATED
    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getBooksPaginated(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        if (q != null && !q.isBlank()) {
            return ResponseEntity.ok(bookService.searchBooks(q, pageable));
        }
        return ResponseEntity.ok(bookService.getBooks(pageable));
    }

    // GET by ID
>>>>>>> Stashed changes
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

<<<<<<< Updated upstream
    // 4. PUT — Update Book
=======
    // UPDATE BOOK
>>>>>>> Stashed changes
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

<<<<<<< Updated upstream
    // 5. DELETE — Delete Book
=======
    // DELETE BOOK
>>>>>>> Stashed changes
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // 6. GET — Get Books with Pagination
    @GetMapping("/paged")
    public ResponseEntity<Page<Book>> getBooksPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bookService.getBooksPaginated(pageable));
    }
}