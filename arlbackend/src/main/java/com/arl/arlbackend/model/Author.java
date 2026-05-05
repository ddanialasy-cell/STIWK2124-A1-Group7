package com.arl.arlbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorID;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String authorName;

    @NotBlank(message = "Author biography is required")
    @Size(min = 2, message = "Biography must be between 2 and 255 characters")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String authorBiography;

    @NotBlank(message = "Author nationality is required")
    @Size(min = 2, max = 255, message = "Nationality must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String authorNationality;

    @ManyToMany
    @JoinTable(
        name = "author_books",
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorBiography() {
        return authorBiography;
    }

    public void setAuthorBiography(String authorBiography) {
        this.authorBiography = authorBiography;
    }

    public String getAuthorNationality() {
        return authorNationality;
    }

    public void setAuthorNationality(String authorNationality) {
        this.authorNationality = authorNationality;
    }

    public Set<Book> getBook() {
        return books;
    }

    public void setBook(Set<Book> books) {
        this.books = books;
    }
    
}
