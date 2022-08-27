import {Component, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {ExpirationLot, PaymentType, Product, SaleItem} from "../../model/sale.type";
import {MatTableDataSource} from '@angular/material/table';
import {ModelSelectedEnum} from "../../model/model.selected.enum";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  form!: FormGroup;
  @ViewChild('productbar') productSearchBar: any;

  dataSource = new MatTableDataSource<SaleItem>([]);
  displayedColumns: string[] = ['product', 'quantity', 'price', 'value'];

  myDataArray: Array<SaleItem> = [];
  modelCustomer = ModelSelectedEnum.customer;
  modelProduct = ModelSelectedEnum.product;
  paymentType = PaymentType;
  appearence: string = environment.appearance;
  quantity: string = '';

  productSelected: Product = {};

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      paymentType: [''],
      customer: [''],
      listItems: this.fb.array([])
    });
  }

  removeItem(i: number) {
    this.myDataArray.splice(i, 1);
    console.info(this.myDataArray);
    this.dataSource.data = this.myDataArray;
  }

  submit() {
    let list = this.form.get('listItems') as FormArray;
    list.clear();
    this.myDataArray.forEach(
      x => {
        list.push(this.fb.group(x));
      });
    console.info(this.form.value);
  }

  selectCustomer(customer: any) {
    console.info('customer')
    console.info(customer)
    this.form.get('customer')!.setValue(customer);
  }

  totalValue() {
    let total = 0;
    this.myDataArray.forEach(x => {
      total += this.getSaleItemPrice(x) * x.quantity!;
    });
    return total.toFixed(2);
  }

  getSaleItemPrice(item: ExpirationLot): number {
    return item.optionalPrice ?? item.product!.price!;
  }

  keyEnterPressed($event: any) {
    if (!this.productSelected.id || this.quantity === '') {
      alert('Selecione um produto e digite a quantidade');
    } else {
      let quantity = parseFloat(this.quantity);

      let item = {product: this.productSelected, quantity: quantity};

      let array = this.form.get('listItems') as FormArray;

      this.myDataArray.push(item);

      array.push(this.fb.group(item));

      this.dataSource.data = this.myDataArray;

      this.productSearchBar.setValueSelected(null);
      this.quantity = '';
    }
  }

  productSelect(product: Product) {
    this.productSelected = product;
  }
}
