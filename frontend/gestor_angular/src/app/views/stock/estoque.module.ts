import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EntryStockView} from './entry-stock/entry-stock.view';
import { SearchStockView } from './search-stock/search-stock.view';
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {SearchBarAutocompleteModule} from "../../components/person-search/search-bar-autocomplete.module";
import {MatInputModule} from "@angular/material/input";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
  declarations: [
    EntryStockView,
    SearchStockView
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    SearchBarAutocompleteModule,
    MatInputModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class EstoqueModule { }
