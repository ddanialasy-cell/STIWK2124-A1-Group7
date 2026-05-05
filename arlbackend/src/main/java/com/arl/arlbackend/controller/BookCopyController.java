package com.arl.arlbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.arl.arlbackend.model.BookCopy;
import com.arl.arlbackend.service.BookCopyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/bookcopy")
public class BookCopyController {

    @Autowired
    private BookCopyService bookCopyService;

    @PostMapping
    public BookCopy createBookCopy(@Valid @RequestBody BookCopy bookCopy) {
        return bookCopyService.saveBookCopy(bookCopy);
    }

    @PostMapping("/book/{bookId}")
    public BookCopy createBookCopyForBook(@PathVariable Long bookId, @Valid @RequestBody BookCopy bookCopy) {
        return bookCopyService.saveBookCopyForBook(bookId, bookCopy);
    }

    @GetMapping
    public List<BookCopy> getAllBookCopies() {
        return bookCopyService.getAllBookCopies();
    }

    @GetMapping("/{id}")
    public BookCopy getBookCopyById(@PathVariable("id") Long bookCopyID) {
        return bookCopyService.getBookCopyById(bookCopyID);
    }

    @DeleteMapping("/{id}")
    public void deleteBookCopy(@PathVariable("id") Long bookCopyID) {
        bookCopyService.deleteBookCopy(bookCopyID);
    }

    @PutMapping("/{id}")
    public BookCopy updateBookCopy(@PathVariable("id") Long bookCopyID, @Valid @RequestBody BookCopy bookCopyDetails) {
        return bookCopyService.updateBookCopy(bookCopyID, bookCopyDetails);
    }

    @GetMapping("/page")
    public Page<BookCopy> getBookCopiesWithPagination(
            @PageableDefault(size = 5) Pageable pageable) {
        return bookCopyService.getBookCopies(pageable);
    }

    @GetMapping("/search")
    public List<BookCopy> searchBookCopies(@RequestParam String bookCopyStatus) {
        return bookCopyService.searchBookCopies(bookCopyStatus);
    }
    
}
