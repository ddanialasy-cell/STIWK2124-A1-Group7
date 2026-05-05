package com.arl.arlbackend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCopyID;

    @NotBlank(message = "Book Copy Status is required")
    @Size(min = 2, max = 255, message = "Status must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String bookCopyStatus;

    @ManyToOne
    @JoinColumn(name = "bookID", nullable = false)
    private Book book;

    public Long getBookCopyID() {
        return bookCopyID;
    }

    public void setBookCopyID(Long bookCopyID) {
        this.bookCopyID = bookCopyID;
    }

    public String getBookCopyStatus() {
        return bookCopyStatus;
    }

    public void setBookCopyStatus(String bookCopyStatus) {
        this.bookCopyStatus = bookCopyStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    
}
