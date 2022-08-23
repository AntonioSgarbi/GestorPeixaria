import {NgModule} from '@angular/core';
import {SearchBarComponent} from "./search-bar.component";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {MatIconModule} from "@angular/material/icon";


@NgModule({
  declarations: [SearchBarComponent],
  exports: [
    SearchBarComponent
  ],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    MatIconModule

  ]
})
export class SearchBarModule {
}
