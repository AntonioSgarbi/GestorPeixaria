import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {ExpirationLot, Product} from "../../model/sale.type";
import {Observable} from "rxjs";
import {Page} from "../../model/page.type";
import {Person} from "../../model/person.type";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient, private router: Router) {
  }

  register(product: Product): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/product`, product);
  }

  search(query: any): Observable<Page<Product>> {
    return this.http.put<Page<Product>>(`${environment.apiUrl}/product`, query);
  }

  findAll(page?: number, size?: number): Observable<Page<Product>> {
    return this.http
      .get<Page<Product>>(`${environment.apiUrl}/product?page=${page ?? 0}&size=${size ?? 10}`);
  }

  edit(product: Product): void {
    this.router.navigate(['/product/edit', product]);
  }

  findAllFiltered(page: number, size: number, filter: any): Observable<Page<Product>> {
    return this.http
      .put<Page<Product>>(`${environment.apiUrl}/product/query?page=${page}&size=${size}`, filter);
  }

  findAllExpirationLot(page: number, size: number, productId: Number): Observable<any> {
    return this.http
      .get<any>(`${environment.apiUrl}/stock/${productId}?page=${page}&size=${size}`);
  }

}
