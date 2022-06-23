import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {EnumTipoQuantidade} from "../shared/tipoQuantidade";

@Component({
  selector: 'app-cadastro',
  templateUrl: './produto-cadastro.component.html',
  styleUrls: ['./produto-cadastro.component.css']
})
export class ProdutoCadastroComponent implements OnInit {
  formulario: FormGroup = new FormGroup({});
  aparencia: string = 'outline';
  tipoQuantidade: any = EnumTipoQuantidade;


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
