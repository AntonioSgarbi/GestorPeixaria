import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModelSelectedEnum} from "../../../model/model.selected.enum";
import {Product} from "../../../model/sale.model";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-registration-person',
  templateUrl: './registration-product.view.html',
  styleUrls: ['./registration-product.view.css']
})
export class RegistrationProductView implements OnInit {
  @ViewChild('supplierSearch') supplierSearchBar: any;
  formGroup: FormGroup = new FormGroup({});
  appearence: string = 'outline';
  modelSearch: any = ModelSelectedEnum.supplier;


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

  limparFormulario() {
    this.formGroup.reset();
    this.supplierSearchBar.dropSelectedValue();
  }

  cadastrar(): void {
    let product: Product = this.formGroup.getRawValue();
    this.productService.register(product).subscribe({
      next: (id: number) => {
        console.log(id);
        this.limparFormulario();
      }
    });
  }

}
