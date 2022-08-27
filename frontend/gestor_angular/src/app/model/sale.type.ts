import {Collaborator, Customer, Supplier} from "./person.type";
import {QuantityType} from "./quantity.type.enum";
import {Time} from "@angular/common";

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

export interface ExpirationLot {
  id?: number;
  product?: Product;
  arrivalDate?: Date;
  expirationDate?: Date;
  availableQuantity?: number;
  optionalPrice?: number;
  arrivalRegisterd?: boolean;
  registeredMoment?: Time;
  supplier?: Supplier;
  receivedBy?: Collaborator;

}

export interface SaleItem {
  id?: number;
  quantity?: number;
  product?: ExpirationLot;
}
