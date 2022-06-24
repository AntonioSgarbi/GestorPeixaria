import {Component, NgIterable, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {EnumTipoPessoa} from "../shared/tipoPessoa";
import {EnumTipoCadastro} from "../shared/tipoCadastro";

@Component({
  selector: 'app-cadastro',
  templateUrl: './pessoa-cadastro.component.html',
  styleUrls: ['./pessoa-cadastro.component.css']
})
export class PessoaCadastroComponent implements OnInit {

  formulario: FormGroup = new FormGroup({});
  aparencia: string = 'outline';
  tipoCadastro: any = EnumTipoCadastro;
  tipoPessoa: any = EnumTipoPessoa;

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.gerarFormulario();
  }

  gerarFormulario(): void {
    this.formulario = this.fb.group({
      "tipoCadastro": [null, Validators.required],
      "nome": [null, Validators.required],
      "tipoPessoa": [null, Validators.required],
      "documento": [],
      "email": [null, Validators.required],
      "dataNascimento": [],
      "telefones": this.fb.array([this.fb.control(null, Validators.required)]),
    })
  }

  get telefones(): FormArray & NgIterable<any> {
    // @ts-ignore
    return  this.formulario.get('telefones')['controls'] as FormArray;
  }

  gerarTelefone(): FormControl {
    return new FormControl(null, Validators.required);
  }

  addTelefone(): void {
    let telefones = this.formulario.get('telefones') as FormArray;
    telefones.push(this.gerarTelefone());
  }

  removeTelefone(index: number): void {
    let telefones = this.formulario.get('telefones') as FormArray;
    telefones.removeAt(index);
  }

  limparFormulario() {
    this.formulario.reset();
  }

  cadastrar(): void {
    console.log(this.formulario.getRawValue());
  }

  mascaraDocumento(): string {
    return this.formulario.get('tipoPessoa')?.value == 'PESSOA_JURIDICA' ? '00.000.000/0000-00' : '000.000.000-00';
  }

  placeholderDocumento(): string {
    return this.formulario.get('tipoPessoa')?.value == 'PESSOA_JURIDICA' ? 'CNPJ' : 'CPF';
  }

  isPessoaFisica(): boolean {
    return this.formulario.get('tipoPessoa')?.value == 'PESSOA_FISICA';
  }

}
