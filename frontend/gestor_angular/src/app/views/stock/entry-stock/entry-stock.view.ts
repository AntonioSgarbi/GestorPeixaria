import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ModelSelectedEnum} from "../../../model/model.selected.enum";
import {environment} from "../../../../environments/environment";
import {MatTableDataSource} from "@angular/material/table";
import {Supplier} from "../../../model/person.model";

@Component({
  selector: 'app-entry-stock',
  templateUrl: './entry-stock.view.html',
  styleUrls: ['./entry-stock.view.css']
})
export class EntryStockView implements OnInit {
  appearence: string = environment.appearance;
  formGroup: FormGroup;
  productModel: ModelSelectedEnum = ModelSelectedEnum.product;
  supplierModel: ModelSelectedEnum = ModelSelectedEnum.supplier;
  dataSource = new MatTableDataSource<Supplier>([]);
  displayedColumns: string[] = ['name', '', 'legalRecordType', 'email'];


  constructor(private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      arrivalRegistered: [true],
      arrivalDate: [null],
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
    console.info(this.formGroup.value);
  }

  supplierSelect(supplier: any) {
    this.formGroup.get('supplier')!.setValue(supplier);
    this.dataSource.data.push(supplier);
  }

  productSelect($event: any) {
    this.formGroup.get('product')!.setValue($event);
  }

  removeItem(row: any) {
    console.log(row);
  }
}
