import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EstoqueEntradaComponent} from './entrada/estoque-entrada.component';
import { EstoquePesquisaComponent } from './pesquisa/estoque-pesquisa.component';


@NgModule({
  declarations: [
    EstoqueEntradaComponent,
    EstoquePesquisaComponent
  ],
  imports: [
    CommonModule
  ]
})
export class EstoqueModule { }
