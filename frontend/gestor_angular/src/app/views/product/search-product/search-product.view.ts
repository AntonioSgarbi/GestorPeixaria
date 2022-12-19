import {Component} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {ExpirationLot, Product} from "../../../model/sale.type";
import {ProductService} from "../product.service";
import {MatTableDataSource} from "@angular/material/table";
import {animate, state, style, transition, trigger} from '@angular/animations';
import {StockService} from "../stock.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-product-stock-search-person',
  templateUrl: './search-product.view.html',
  styleUrls: ['./search-product.view.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SearchProductView {
  private stockService: StockService;

  productDataSource: MatTableDataSource<Product> = new MatTableDataSource<Product>([]);
  expirationLotDataSource: MatTableDataSource<ExpirationLot> = new MatTableDataSource<ExpirationLot>([]);

  productDisplayedColumns: string[] = ['name', 'quantityType', 'price'];
  expirationLotDisplayedColumns: string[] = ['supplier', 'arrivalDate', 'expirationDate', 'optionalPrice'];

  columnsToDisplayWithExpand = [...this.productDisplayedColumns, 'expand'];
  expandedElement: any;
  isLoadingRow: boolean = false;


  constructor(private productService: ProductService, private http: HttpClient) {
    this.stockService = new StockService(http);

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
    price = parseFloat(price).toFixed(2);
    return `R$ ${price}`
  }

  // formatDate(date: string): string {
  //   return `${date.substring()} / ${} / ${}`
  // }

  productClicked(element: any) {
    this.isLoadingRow = true;
    this.expandedElement = this.expandedElement === element ? null : element;

    this.stockService.findAllByProductId(element.id).subscribe({
      next: (data) => {
        this.expirationLotDataSource.data = data.content;
              this.isLoadingRow = false;
              console.log(data)
      },
      error: (err) => {
        this.isLoadingRow = false;
        console.log('\n\nfailed');
      },
    });
  }
}
