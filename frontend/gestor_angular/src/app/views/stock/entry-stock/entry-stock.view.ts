import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ModelSelectedEnum} from "../../../model/model.selected.enum";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-entry-stock',
  templateUrl: './entry-stock.view.html',
  styleUrls: ['./entry-stock.view.css']
})
export class EntryStockView implements OnInit {
  appearence: string = environment.appearance;
  formGroup: FormGroup;
  product: ModelSelectedEnum = ModelSelectedEnum.product;
  supplier: ModelSelectedEnum = ModelSelectedEnum.supplier;

  constructor(private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      arrivalRegistered: [true],
      arrivalDate: [null],
      arrivalQuantity: [null],
    });
  }

  ngOnInit(): void {
  }

}
