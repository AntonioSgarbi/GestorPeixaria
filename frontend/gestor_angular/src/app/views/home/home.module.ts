import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from "./home.component";
import {SearchBarAutocompleteModule} from "../../components/person-search/search-bar-autocomplete.module";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    SearchBarAutocompleteModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    MatInputModule
  ]
})
export class HomeModule { }
