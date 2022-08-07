import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {QuantityType} from "../shared/quantity.type.enum";
import {ModelSelectedEnum} from "../../../model/model.selected.enum";

@Component({
  selector: 'app-registration-person',
  templateUrl: './registration-product.view.html',
  styleUrls: ['./registration-product.view.css']
})
export class RegistrationProductView implements OnInit {
  formGroup: FormGroup = new FormGroup({});
  appearence: string = 'outline';
  modelSearch: any = ModelSelectedEnum.supplier;


  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initForm();

  }

  initForm(): void {
    this.formGroup = this.fb.group({
      name: [],
      quantityType: ['', Validators.required],
      price: [null, Validators.required],
    });
  }

  limparFormulario() {
    this.formGroup.reset();
  }

  cadastrar(): void {
    console.log(this.formGroup.getRawValue());
  }

}
