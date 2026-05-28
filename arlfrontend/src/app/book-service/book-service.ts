import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book, BookPageResponse } from '../book/book';

@Injectable({
  providedIn: 'root'
})

export class BookService {

  private apiUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {}

  getBooks(page: number = 0, size: number = 10, search?: string): Observable<BookPageResponse> {
    let params = new HttpParams().set('page', page.toString()).set('size', size.toString());

    if (search && search.trim() !== '') {
      params = params.set('search', search);
    }

    return this.http.get<BookPageResponse>(this.apiUrl, {params});
  }

  addBook(bookData: Book): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, bookData);
  }

  getBookById(id: number) :Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  editBook(id: number, bookData: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, bookData);
  }

  removeBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
