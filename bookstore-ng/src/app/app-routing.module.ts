import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorControllerComponent } from './author-controller/author-controller.component';
import { AuthorDetailsComponent } from './author-details/author-details.component';
import { AuthorComponent } from './author/author.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'home', component: HomeComponent},
  { path: 'authors', component: AuthorControllerComponent,
    children: [
      {path: '', component: AuthorComponent},
      {path: ':id', component: AuthorDetailsComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
