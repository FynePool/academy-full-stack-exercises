import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorComponent } from './author/author.component';
import { HomeComponent } from './home/home.component';
import { AuthorDetailsComponent } from './author-details/author-details.component';
import { AuthorControllerComponent } from './author-controller/author-controller.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AuthorListComponent,
    AuthorComponent,
    HomeComponent,
    AuthorDetailsComponent,
    AuthorControllerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
