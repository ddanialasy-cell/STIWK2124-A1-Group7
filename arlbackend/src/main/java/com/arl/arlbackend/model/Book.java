package com.arl.arlbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String bookTitle;

    @NotBlank(message = "Description is required")
    @Size(min = 2, message = "Description must be between 2 and 255 characters")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String bookDescription;
    
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 255, message = "Category must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String bookCategory;

    @NotBlank(message = "Genre is required")
    @Size(min = 2, max = 255, message = "Genre must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String bookGenre;

    @NotBlank(message = "Publish Date is required")
    @Size(min = 2, max = 255, message = "Publish Date must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String bookPublishDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<BookCopy> bookCopy = new HashSet<>();

    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    private Set<Author> author = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "publisherID", nullable = false)
    private Publisher publisher;

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookPublishDate() {
        return bookPublishDate;
    }

    public void setBookPublishDate(String bookPublishDate) {
        this.bookPublishDate = bookPublishDate;
    }

    public Set<BookCopy> getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(Set<BookCopy> bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
