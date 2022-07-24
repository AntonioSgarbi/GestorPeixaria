import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {SaleItem} from "../person/model/sale.model";
import {Observable, from} from "rxjs";
import { MatTableDataSource } from '@angular/material/table';

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
    { product: {id: 1, name: 'espada', price: 29.9}, quantity: 3.200},
    { product: {id: 2, name: 'lagosta', price: 79.9}, quantity: 1.000},
    { product: {id: 3, name: 'camarão', price: 49.9}, quantity: 1.500},
    ]

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      paymentType: [''],
      customer: [''],
      listItems: this.fb.array([
        this.fb.group({
          product: this.fb.group({
            id: [],
            name: [''],
            price: [],
          }),
          quantity: []
        }),
        this.fb.group({
          product: this.fb.group({
            id: [],
            name: [''],
            price: [],
          }),
          quantity: []
        }),
        this.fb.group({
          product: this.fb.group({
            id: [],
            name: [''],
            price: [],
          }),
          quantity: []
        }),
      ])
    });

    this.dataSource.data = this.myDataArray;

    // this.getListItems().subscribe({
    //   next: (value) => {
    //     // @ts-ignore
    //     this.dataSource.data = value;
    //   }
    // })
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
    this.myDataArray.splice(i,1);
    console.info(this.myDataArray);
    this.dataSource.data = this.myDataArray;
  }

  submit() {
    this.form.get('listItems')?.setValue(this.myDataArray);
    console.info(this.form.value);
  }
}
