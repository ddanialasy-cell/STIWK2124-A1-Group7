package com.arl.arlbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.arl.arlbackend.model.Author;
import com.arl.arlbackend.service.AuthorService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PostMapping("/book/{bookId}")
    public Author createAuthorForBook(@PathVariable Long bookId, @Valid @RequestBody Author author) {
        return authorService.saveAuthorForBook(bookId, author);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") Long authorID) {
        return authorService.getAuthorById(authorID);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorID) {
        authorService.deleteAuthor(authorID);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable("id") Long authorID, @Valid @RequestBody Author authorDetails) {
        return authorService.updateAuthor(authorID, authorDetails);
    }

    @GetMapping("/page")
    public Page<Author> getAuthorsWithPagination(
            @PageableDefault(size = 5) Pageable pageable) {
        return authorService.getAuthors(pageable);
    }

    @GetMapping("/search")
    public List<Author> searchAuthors(@RequestParam String authorName) {
        return authorService.searchAuthors(authorName);
    }

}
