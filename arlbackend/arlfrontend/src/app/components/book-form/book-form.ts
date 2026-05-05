import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookService } from '../../services/book';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './book-form.html',
  styleUrl: './book-form.css'
})
export class BookFormComponent implements OnInit {
  @Input() book: any = null;
  @Output() close = new EventEmitter<void>();

<<<<<<< HEAD
=======
  validationErrors: any = {};
  submitError: string = '';

>>>>>>> feature/add-new-book-modification
  formData = {
    title: '',
    author: '',
    category: '',
    description: ''
  };

<<<<<<< HEAD
  ngOnInit(): void {
    if (this.book) {
      this.formData = { ...this.book };
    }
  }

  constructor(private bookService: BookService) {}

  submit(): void {
    if (this.book) {
      this.bookService.updateBook(this.book.id, this.formData).subscribe(() => {
        this.close.emit();
      });
    } else {
      this.bookService.createBook(this.formData).subscribe(() => {
        this.close.emit();
      });
    }
=======
  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    if (this.book) {
      this.formData = {
        title: this.book.title,
        author: this.book.author,
        category: this.book.category,
        description: this.book.description
      };
    }
  }

  submit(): void {
    this.validationErrors = {};
    this.submitError = '';

    if (!this.formData.title.trim() || !this.formData.author.trim() ||
        !this.formData.category.trim() || !this.formData.description.trim()) {
      if (!this.formData.title.trim()) this.validationErrors['title'] = 'Title is required';
      if (!this.formData.author.trim()) this.validationErrors['author'] = 'Author is required';
      if (!this.formData.category.trim()) this.validationErrors['category'] = 'Category is required';
      if (!this.formData.description.trim()) this.validationErrors['description'] = 'Description is required';
      return; 
    }

    const request$ = this.book
      ? this.bookService.updateBook(this.book.id, this.formData)
      : this.bookService.createBook(this.formData);

    request$.subscribe({
      next: () => this.close.emit(),
      error: (err) => {
        if (err.status === 400 && err.error?.fields) {
          // Show field-level validation errors from Spring Boot
          this.validationErrors = err.error.fields;
        } else if (err.status === 404) {
          this.submitError = 'Book not found. It may have been deleted.';
        } else {
          this.submitError = 'Something went wrong. Please try again.';
        }
      }
    });
>>>>>>> feature/add-new-book-modification
  }

  cancel(): void {
    this.close.emit();
  }
}