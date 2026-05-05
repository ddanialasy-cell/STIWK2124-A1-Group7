package com.arl.arlbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherID;

    @NotBlank(message = "Publisher Name is required")
    @Size(min = 2, max = 255, message = "Publisher name must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String publisherName;

    @NotBlank(message = "Publisher Contact is required")
    @Size(min = 2, max = 255, message = "Publisher contact must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String publisherContact;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Book> book = new HashSet<>();

    public Long getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Long publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherContact() {
        return publisherContact;
    }

    public void setPublisherContact(String publisherContact) {
        this.publisherContact = publisherContact;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

}
