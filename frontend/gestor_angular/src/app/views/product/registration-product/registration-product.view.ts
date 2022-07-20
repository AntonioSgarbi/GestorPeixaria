import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {TipoQuantidade} from "../shared/enumTipoQuantidade";

@Component({
  selector: 'app-registration-person',
  templateUrl: './registration-product.view.html',
  styleUrls: ['./registration-product.view.css']
})
export class RegistrationProductView implements OnInit {
  formulario: FormGroup = new FormGroup({});
  aparencia: string = 'outline';
  tipoQuantidade: any = TipoQuantidade;


  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initForm();

  }

  initForm(): void {
    this.formulario = this.fb.group({
      nome: [],
      tipoQuantidade: []
    });
  }

  limparFormulario() {
    this.formulario.reset();
  }

  cadastrar(): void {
    console.log(this.formulario.getRawValue());
  }

}
