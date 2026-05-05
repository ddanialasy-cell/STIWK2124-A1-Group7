import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookService } from '../../services/book';
import { BookFormComponent } from '../book-form/book-form';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [CommonModule, FormsModule, BookFormComponent],
  templateUrl: './book-list.html',
  styleUrl: './book-list.css'
})
export class BookListComponent implements OnInit {
  books: any[] = [];
  searchTitle: string = '';
  showForm: boolean = false;
  selectedBook: any = null;
  errorMessage: string = '';

  // Pagination
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.errorMessage = '';
    this.bookService.getBooks(this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.books = data.content;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error: () => {
        this.errorMessage = 'Failed to load books. Is the backend running?';
      }
    });
  }

  searchBooks(): void {
    this.errorMessage = '';
    if (this.searchTitle.trim()) {
      this.currentPage = 0;
      this.bookService.searchBooks(this.searchTitle, this.currentPage, this.pageSize).subscribe({
        next: (data) => {
          this.books = data.content;
          this.totalPages = data.totalPages;
          this.totalElements = data.totalElements;
        },
        error: () => {
          this.errorMessage = 'Search failed. Please try again.';
        }
      });
    } else {
      this.currentPage = 0;
      this.loadBooks();
    }
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      if (this.searchTitle.trim()) {
        this.searchBooks();
      } else {
        this.loadBooks();
      }
    }
  }

  deleteBook(id: number): void {
    if (confirm('Delete this book?')) {
      this.bookService.deleteBook(id).subscribe({
        next: () => this.loadBooks(),
        error: (err) => {
          this.errorMessage = err.error?.message || 'Failed to delete book.';
        }
      });
    }
  }

  editBook(book: any): void {
    this.selectedBook = book;
    this.showForm = true;
  }

  addBook(): void {
    this.selectedBook = null;
    this.showForm = true;
  }

  onFormClose(): void {
    this.showForm = false;
    this.loadBooks();
  }

  readAloud(book: any): void {
    const text = `Title: ${book.title}. Author: ${book.author}. ${book.description || ''}`;
    const utterance = new SpeechSynthesisUtterance(text);
    window.speechSynthesis.speak(utterance);
  }
}