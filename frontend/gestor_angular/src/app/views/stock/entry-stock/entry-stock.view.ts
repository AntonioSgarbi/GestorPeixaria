import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModelSelectedEnum} from "../../../model/model.selected.enum";
import {environment} from "../../../../environments/environment";
import {MatTableDataSource} from "@angular/material/table";
import {Supplier} from "../../../model/person.type";
import {HttpClient} from "@angular/common/http";
import {StockService} from "../stock.service";
import {ExpirationLot} from "../../../model/sale.type";

@Component({
  selector: 'app-entry-stock',
  templateUrl: './entry-stock.view.html',
  styleUrls: ['./entry-stock.view.css']
})
export class EntryStockView implements OnInit {
  stockService: StockService;
  appearence: string = environment.appearance;
  formGroup: FormGroup;
  productModel: ModelSelectedEnum = ModelSelectedEnum.product;
  supplierModel: ModelSelectedEnum = ModelSelectedEnum.supplier;
  dataSource = new MatTableDataSource<Supplier>([]);
  displayedColumns: string[] = ['name', '', 'legalRecordType', 'email'];


  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.stockService = new StockService(http);

    this.formGroup = this.fb.group({

      arrivalRegistered: [true],

      arrivalDate: [null],
      expirationDate: [null, Validators.required],

      arrivalQuantity: [null],

      product: [null],
      supplier: [null]
    });
  }

  ngOnInit(): void {
  }

  placeholderSupplier(): string {
    return 'Digite o nome de um fornecedor e selecione na lista...';
  }

  placeholderProduct(): string {
    return 'Digite o nome de um produto e selecione na lista...';
  }

  placeholderQuantity(): string | undefined {
    let product = this.formGroup.get('product')!.value;
    if (product) {
      let quantityType = product.quantityType;
      return quantityType == 'UNITY' ? '0' : '0.00';
    } else return '0.00';
  }

  submit(): void {
    if (this.formGroup.valid) {
      this.stockService.entryRegister(this.formGroup.getRawValue() as ExpirationLot).subscribe({
        next: (data) => {
          console.log(data)
        },
        error: (err) => {
          console.log(err)
        }
      })
    }
    console.info(this.formGroup.value);
  }

  supplierSelect(supplier: any) {
    this.formGroup.get('supplier')!.setValue(supplier);
    this.dataSource.data.push(supplier);
  }

  productSelect(product: any) {
    console.log(product)
    this.formGroup.get('product')!.setValue(product);
  }

}
