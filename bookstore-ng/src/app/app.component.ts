import { Component, OnInit} from '@angular/core';
import { FormGroup, FormControl, FormArray, NgForm } from '@angular/forms'
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {

  searchString?: string
  title = 'bookstore-ng';

  constructor(private _router: Router) {}

  ngOnInit(): void {
  }

  submit(form: NgForm){
    console.log(form.value);
  }
}
