import {LegalRecordType} from "./legal.record.type.enum";
import {Sale} from "./sale.model";

export interface Person {
  id?: number;
  document?: string;
  name?: string;
  legalRecordType?: LegalRecordType;
  phones?: Set<string>;
  email?: string;
}

export interface Customer extends Person {
  purchases?: Set<Sale>;
}

export interface Collaborator extends Person {
  sales?: Set<Sale>;
  wage?: number;
}

export interface Supplier extends Person {
}
