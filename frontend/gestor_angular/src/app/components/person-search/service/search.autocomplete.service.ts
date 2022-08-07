import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../environments/environment";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class SearchAutocompleteService {

  constructor(private http: HttpClient) {
  }

  searchText(model: string, search: string): Observable<any> {
    let body = { name: search };
    return this.http.put(`${environment.apiUrl}/${model}/query`, body);
  }

  searchNumber(model: string, search: string): Observable<any> {
    return this.http.put(`${environment.apiUrl}/${model}/query`, {document: search});
  }


}
