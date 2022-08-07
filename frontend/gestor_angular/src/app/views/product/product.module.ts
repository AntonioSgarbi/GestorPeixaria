import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegistrationProductView} from './registration-product/registration-product.view';
import {MatCardModule} from "@angular/material/card";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import { SearchProductView } from './search-product/search-product.view';
import {SearchBarAutocompleteModule} from "../../components/person-search/search-bar-autocomplete.module";


@NgModule({
  declarations: [
    RegistrationProductView,
    SearchProductView
  ],
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatInputModule,
        MatFormFieldModule,

        MatCardModule,
        MatSelectModule,
        MatButtonModule,
        SearchBarAutocompleteModule
    ]
})
export class ProductModule {
}
