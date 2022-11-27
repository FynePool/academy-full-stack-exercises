import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Author } from '../shared/author-interface';
import { AuthorService } from '../shared/author.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {
  info: boolean = false
  authorsList: Author[] = [];

  constructor(private authorService: AuthorService, private _router: Router) { }

  ngOnInit(): void {
    this.authorService.getAuthors().subscribe(authors => this.authorsList = authors);
  }

  onDeleteAuthor(author: Author): void {
    this.authorService.deleteAuthor(author).subscribe(
      (s) => {
        this.authorService.getAuthors().subscribe(authorsList => this.authorsList = authorsList);
      }
    )
  }

  onInfo(author: Author): void{
    this.info = true;
  }

  onEdit(author: Author): void{
    this.authorService.editAuthor(author).subscribe(
      () => {
        this.authorService.getAuthors().subscribe(authorsList => this.authorsList = authorsList)
      }
    )
  }
}
