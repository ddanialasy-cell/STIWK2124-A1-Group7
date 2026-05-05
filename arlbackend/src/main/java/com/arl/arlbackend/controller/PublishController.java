package com.arl.arlbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.arl.arlbackend.model.Publisher;
import com.arl.arlbackend.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublishController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @PostMapping("/book/{bookId}")
    public Publisher createPublisherForBook(@PathVariable Long bookId, @Valid @RequestBody Publisher publisher) {
        return publisherService.savePublisherForBook(bookId, publisher);
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable("id") Long publisherID) {
        return publisherService.getPublisherById(publisherID);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable("id") Long publisherID) {
        publisherService.deletePublisher(publisherID);
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable("id") Long publisherID, @Valid @RequestBody Publisher publisherDetails) {
        return publisherService.updatePublisher(publisherID, publisherDetails);
    }

    @GetMapping("/page")
    public Page<Publisher> getPublishersWithPagination(
            @PageableDefault(size = 5) Pageable pageable) {
        return publisherService.getPublishers(pageable);
    }

    @GetMapping("/search")
    public List<Publisher> searchPublishers(@RequestParam String publisherName) {
        return publisherService.searchPublishers(publisherName);
    }

}
