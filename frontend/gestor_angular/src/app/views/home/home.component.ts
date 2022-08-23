import {Component, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {PaymentType, Product, SaleItem} from "../../model/sale.model";
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
    // let array = this.form.get('listItems') as FormArray;
    // this.myDataArray.forEach(x => {
    //   array.push(this.fb.group(x))
    // });
    //
    // this.dataSource.data = this.myDataArray;
  }

  addSaleItem(): void {
    this.myDataArray.push({id: this.myDataArray.length + 1, product: {name: '', price: 0}, quantity: 0});

    let array = this.form.get('listItems') as FormArray;
    array.push(this.fb.group({
      item: [''],
      quantity: [''],
    }));
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
      x.product?.price ? total += x.product.price * x.quantity! : total += 0;
    });
    return total.toFixed(2);
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
