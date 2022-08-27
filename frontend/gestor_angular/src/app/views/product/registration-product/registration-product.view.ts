import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModelSelectedEnum} from "../../../model/model.selected.enum";
import {Product} from "../../../model/sale.type";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-registration-person',
  templateUrl: './registration-product.view.html',
  styleUrls: ['./registration-product.view.css']
})
export class RegistrationProductView implements OnInit {
  formGroup: FormGroup = new FormGroup({});
  appearence: string = 'outline';


  constructor(private fb: FormBuilder, private productService: ProductService) {
  }

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

  reset() {
    this.formGroup.reset();
  }

  submit(): void {
    let product: Product = this.formGroup.getRawValue();
    this.productService.register(product).subscribe({
      next: (id: number) => {
        console.log(id);
        this.reset();
      }
    });
  }

}
