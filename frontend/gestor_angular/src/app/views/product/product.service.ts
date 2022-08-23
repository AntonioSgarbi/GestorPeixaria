import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Product} from "../../model/sale.model";
import {Observable} from "rxjs";
import {Page} from "../../model/page";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  register(product: Product): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/product`, product);
  }

  search(query: any): Observable<Page<Product>> {
    return this.http.put<Page<Product>>(`${environment.apiUrl}/product`, query);
  }


}
