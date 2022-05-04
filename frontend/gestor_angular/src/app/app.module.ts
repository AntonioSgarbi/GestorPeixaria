import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PessoaModule} from './pages/pessoa/pessoa.module';
import {MAT_DATE_LOCALE} from "@angular/material/core";
import {NavbarModule} from "./components/navbar/navbar.module";
import {HttpClientModule} from "@angular/common/http";
import {HomeModule} from "./pages/home/home.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,


    PessoaModule,
    NavbarModule,
    HomeModule

  ],
  providers: [
    {
      provide: MAT_DATE_LOCALE,
      useValue: 'pt-BR'
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
