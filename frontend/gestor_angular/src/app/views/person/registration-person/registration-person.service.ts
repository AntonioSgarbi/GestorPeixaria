import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {Cliente, Fornecedor, Funcionario, Pessoa} from "../model/person.model";

@Injectable({
  providedIn: 'root'
})
export class RegistrationPersonService {

  constructor(private http: HttpClient) { }

  cadastrarCliente(client: Cliente): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/cliente`, client)
  }

  cadastrarFornecedor(provider: Fornecedor): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/fornecedor`, provider)
  }

  cadastrarFuncionario(employee: Funcionario): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/funcionario`, employee)
  }
}
