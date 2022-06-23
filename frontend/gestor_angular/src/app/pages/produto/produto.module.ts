import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProdutoCadastroComponent} from './cadastro/produto-cadastro.component';
import {MatCardModule} from "@angular/material/card";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
  declarations: [
    ProdutoCadastroComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,

    MatCardModule,
    MatSelectModule,
    MatButtonModule
  ]
})
export class ProdutoModule {
}
