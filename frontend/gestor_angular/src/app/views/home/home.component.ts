import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {PaymentType, SaleItem} from "../person/model/sale.model";
import {MatTableDataSource} from '@angular/material/table';
import {ModelSelectedEnum} from "../../components/person-search/model.selected.enum";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  form!: FormGroup;

  dataSource = new MatTableDataSource<SaleItem>([]);
  displayedColumns: string[] = ['product', 'quantity', 'price', 'value'];
  myDataArray: Array<SaleItem> = [
    {product: {id: 1, name: 'espada', price: 29.9}, quantity: 3.200},
    {product: {id: 2, name: 'lagosta', price: 79.9}, quantity: 1.000},
    {product: {id: 3, name: 'camarão', price: 49.9}, quantity: 1.500},
  ]
  modelCustomer = ModelSelectedEnum.customer;
  modelProduct = ModelSelectedEnum.product;
  paymentType = PaymentType;
  appearence: string = environment.appearance;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      paymentType: [''],
      customer: [''],
      listItems: this.fb.array([])
    });
    let array = this.form.get('listItems') as FormArray;
    this.myDataArray.forEach(x => { array.push(this.fb.group(x)) });

    this.dataSource.data = this.myDataArray;
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
}
