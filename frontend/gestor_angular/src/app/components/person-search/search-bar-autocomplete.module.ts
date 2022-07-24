import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SearchBarAutocompleteComponent} from "./search-bar-autocomplete.component";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";



@NgModule({
  declarations: [SearchBarAutocompleteComponent],
  exports: [
    SearchBarAutocompleteComponent
  ],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule

  ]
})
export class SearchBarAutocompleteModule { }
