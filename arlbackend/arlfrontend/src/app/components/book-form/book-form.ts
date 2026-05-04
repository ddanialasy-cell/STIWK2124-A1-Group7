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

  formData = {
    title: '',
    author: '',
    category: '',
    description: ''
  };

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
  }

  cancel(): void {
    this.close.emit();
  }
}