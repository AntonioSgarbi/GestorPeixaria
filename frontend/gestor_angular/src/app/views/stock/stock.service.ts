import {HttpClient} from "@angular/common/http";
import { Observable } from "rxjs";
import {ExpirationLot} from "../../model/sale.type";
import {environment} from "../../../environments/environment";


export class StockService {
  constructor(private http: HttpClient) {
  }

  public entryRegister(entry: ExpirationLot): Observable<Number> {
    return this.http.post<number>(`${environment.apiUrl}/stock`, entry);
  }

}
