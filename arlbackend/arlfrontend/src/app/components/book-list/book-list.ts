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

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getAllBooks().subscribe(data => {
      this.books = data;
    });
  }

  searchBooks(): void {
    if (this.searchTitle.trim()) {
      this.bookService.searchBooks(this.searchTitle).subscribe(data => {
        this.books = data;
      });
    } else {
      this.loadBooks();
    }
  }

  deleteBook(id: number): void {
    if (confirm('Delete this book?')) {
      this.bookService.deleteBook(id).subscribe(() => {
        this.loadBooks();
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