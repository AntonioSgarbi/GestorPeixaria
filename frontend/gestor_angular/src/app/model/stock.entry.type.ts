import { Collaborator, Supplier } from "./person.type";
import { Product } from "./sale.type";

export interface StockEntry {
    arrivalDate: Date;
    expirationLot: Date;
    arrivalQuantity: number;
    availableQuantity: number;
    supplierPrice: number;
    optionalPrice: number;
    product: Product;
    collaborator: Collaborator;
    supplier: Supplier;
    arrivavelRegistered: boolean;

}