import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import {ExpirationLot, Product} from 'src/app/model/sale.type';
import {ProductService} from "../../product/product.service";
import {PageEvent} from "@angular/material/paginator";
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-stock-search-person',
  templateUrl: './search-stock.view.html',
  styleUrls: ['./search-stock.view.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SearchStockView {
  productDataSource: MatTableDataSource<Product> = new MatTableDataSource<Product>([]);
  expirationLotDataSource: MatTableDataSource<ExpirationLot> = new MatTableDataSource<ExpirationLot>([]);
  productDisplayedColumns: string[] = ['name', 'quantityType', 'price'];
  expirationLotDisplayedColumns: string[] = ['supplier', 'expirationDate', 'optionalPrice'];
  columnsToDisplayWithExpand = [...this.productDisplayedColumns, 'expand'];
  expandedElement: any;
  isLoadingRow: boolean = false;


  constructor(private productService: ProductService) {
    this.productService.findAll().subscribe({
      next: (data) => {
        this.productDataSource.data = data.content;
      }
    });
  }

  setPage($event: PageEvent) {

  }

  expirationLotClicked(row: Product) {
    console.log(row)

  }

  formatQuantityType(quantityType: string): string {
    return quantityType == 'WEIGHT' ? 'Peso' : 'Unidade';
  }

  formatPrice(price: string): string {
    return parseFloat(price).toFixed(2);
  }

  productClicked(element: any) {

    this.isLoadingRow = true;
    this.expandedElement = this.expandedElement === element ? null : element;
    this.productService
      .findAllExpirationLot(0, 0, element)
      .subscribe({
        next: (data) => {
          this.expirationLotDataSource.data = data;
          this.isLoadingRow = false;
        },
        error: (err) => {
          this.isLoadingRow=false;
          console.log('\n\nfailed');
        },
      });
    console.log(element)
  }

}
