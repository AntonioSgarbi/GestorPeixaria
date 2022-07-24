import {Customer, Collaborator} from "./person.model";
import {QuantityType} from "../../product/shared/quantity.type.enum";

export interface Sale {
  id?: number;
  moment?: any;
  paymentType?: PaymentType;
  customer?: Customer;
  collaborator?: Collaborator;
  saleItems?: SaleItem;
  totalValue?: number;
}

export enum PaymentType {
  CASH = 'Dinheiro',
  DEBIT_CARD = 'Cartão de Débito',
  CREDIT_CARD = 'Cartão de Crédito',
}

export interface Product {
  id?: number;
  name?: string;
  price?: number;
  quantityType?: QuantityType;
  availableQuantity?: number;
}

export interface SaleItem {
  id?: number;
  quantity?: number;
  product?: Product;
}
