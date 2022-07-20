import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EntryStockView} from './entry-stock/entry-stock.view';
import { SearchStockView } from './search-stock/search-stock.view';


@NgModule({
  declarations: [
    EntryStockView,
    SearchStockView
  ],
  imports: [
    CommonModule
  ]
})
export class EstoqueModule { }
