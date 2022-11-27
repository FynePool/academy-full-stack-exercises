import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Author } from '../shared/author-interface';
import { AuthorService } from '../shared/author.service';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css']
})
export class AuthorDetailsComponent implements OnInit {
  @Output() deleted = new EventEmitter<Author>();
  author: Author | undefined ;

  constructor(route: ActivatedRoute, private authorService: AuthorService) {
    console.log(route.snapshot.paramMap.get('id'))
    this.authorService.getAuthor(Number(route.snapshot.paramMap.get('id'))).subscribe( (author) => this.author = author)
   }

  ngOnInit(): void {
  }

}
