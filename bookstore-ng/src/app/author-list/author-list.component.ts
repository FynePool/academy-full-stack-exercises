import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, FormArray, NgForm } from '@angular/forms'
import { Author } from '../shared/author-interface';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css'],
})
export class AuthorListComponent implements OnInit {
  @Input() authorsList: Author[] = [];
  @Output() deleted = new EventEmitter<Author>();
  @Output() info = new EventEmitter();
  @Output() update = new EventEmitter();
  @Output() edit = new EventEmitter();

  searchFN?: string
  searchLN?: string
  searchID?: number
  inSearch: boolean = false
  editFN?: string
  editLN?: string
  editID?: number

  constructor() {}

  ngOnInit(): void {}

  submitSearchAuthors(form: NgForm){
    console.log(form.value);
    var authorSearchList: Author[] = []
    for ( let a of this.authorsList ){
      if( form.controls['searchFN'].value != undefined && a.firstName.toLowerCase().includes(form.controls['searchFN'].value.toLowerCase())){
        authorSearchList.push(a)
      }
      else if ( form.controls['searchLN'].value != undefined && a.lastName.toLowerCase().includes(form.controls['searchLN'].value.toLowerCase()) ){
        authorSearchList.push(a)
      }
      else if ( form.controls['searchID'].value != undefined && a.id === Number(form.controls['searchID'].value) ) {
        authorSearchList.push(a)
      }
    }
    this.authorsList = authorSearchList
    this.inSearch = true
  }

  submitEditAuthors(form: NgForm){
    var author!: Author;
    if( form.controls['editFN'].value != undefined ){
      author.firstName = form.controls['editFN'].value
    }
    else if ( form.controls['editLN'].value != undefined ){
      author.firstName = form.controls['editLN'].value
    }
    else if ( form.controls['editID'].value != undefined ) {
      author.firstName = form.controls['editID'].value
    }

    this.edit.emit(author)
  }

  backToFullList(){
    this.update.emit()
    this.inSearch = false
  }
}
