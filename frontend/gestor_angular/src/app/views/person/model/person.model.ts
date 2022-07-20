import {LegalRecordType} from "./legal.record.type.enum";
import {Venda} from "./sale.model";

export interface Pessoa {
  id?: number;
  documento?: string;
  nome?: string;
  pessoaTipo?: LegalRecordType;
  telefones?: Set<string>;
  email?: string;
}

export interface Cliente extends Pessoa {
  compras?: Set<Venda>;
}

export interface Funcionario extends Pessoa {
  vendas?: Set<Venda>;
  salario?: number;
}

export interface Fornecedor extends Pessoa { }
