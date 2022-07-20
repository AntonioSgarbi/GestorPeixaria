import {Cliente, Funcionario} from "./person.model";
import {TipoQuantidade} from "../../product/shared/enumTipoQuantidade";

export interface Venda {
  id?: number;
  momentoLancamento?: any;
  pagamentoTipo?: TipoPagamento;
  cliente?: Cliente;
  funcionario?: Funcionario;
  produtosQuantidades?: ItemCompra;
  valorTotal?: number;
}

export enum TipoPagamento {
  DINHEIRO = 'Dinheiro',
  CARTAO_CREDITO = 'Cartão de Crédito',
  CARTAO_DEBITO = 'Cartão de Débito',
}

interface Produto {
  id?: number;
  nome?: string;
  tipoQuantidade?: TipoQuantidade;
  quantidadeEstoque?: number;
}

interface ItemCompra {
  id?: number;
  quantidade?: number;
  produto?: Produto;
}
