import {Component, OnInit} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {Product} from "../../../model/sale.model";
import {ProductService} from "../product.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-product-stock-search-person',
  templateUrl: './search-product.view.html',
  styleUrls: ['./search-product.view.css']
})
export class SearchProductView {
  dataSource: MatTableDataSource<Product> = new MatTableDataSource<Product>([]);
  displayedColumns: string[] = ['name', 'quantityType', 'price'];
  // columnsToDisplayWithExpand = [...this.displayedColumns, 'expand'];
  // expandedElement: any;


  constructor(private productService: ProductService) {
    this.productService.findAll().subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      }
    });
  }

  setPage($event: PageEvent) {

  }

  productClicked(row: Product) {

  }

  formatQuantityType(quantityType: string): string {
    return quantityType == 'WEIGHT' ? 'Peso' : 'Unidade';
  }

  formatPrice(price: string): string {
    return parseFloat(price).toFixed(2);
  }
}
