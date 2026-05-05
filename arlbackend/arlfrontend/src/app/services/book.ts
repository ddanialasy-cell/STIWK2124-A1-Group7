import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {}

<<<<<<< HEAD
  getAllBooks(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
=======
  getBooks(page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.get<any>(this.apiUrl, { params });
>>>>>>> feature/add-new-book-modification
  }

  getBookById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createBook(book: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, book);
  }

  updateBook(id: number, book: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, book);
  }

  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

<<<<<<< HEAD
  searchBooks(title: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/search?title=${title}`);
  }

  getBooksWithPagination(page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.get<any>(`${this.apiUrl}/page`, { params });
=======
  searchBooks(q: string, page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('q', q)
      .set('page', page)
      .set('size', size);
    return this.http.get<any>(this.apiUrl, { params });
>>>>>>> feature/add-new-book-modification
  }
}