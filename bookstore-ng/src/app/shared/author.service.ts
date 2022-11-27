import { formatDate } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from, Observable, of, fromEvent } from 'rxjs';
import { Author } from './author-interface';

@Injectable({
  providedIn: 'root',
})
export class AuthorService {

  constructor(private http: HttpClient) {}

  getAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>('http://localhost:8080/author')

  }

  getAuthor(id: number): Observable<Author | undefined> {
    return this.http.get<Author>('http://localhost:8080/author/'+id)
  }

  deleteAuthor(author: Author): Observable<string> {
    return this.http.delete<string>('http://localhost:8080/author/'+author.id)
  }

  editAuthor(author: Author): Observable<any> {
    return this.http.put('https://localhost:8080/author/'+author.id, author);
  }

}
