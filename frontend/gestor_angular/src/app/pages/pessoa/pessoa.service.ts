import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private http: HttpClient) {
  }


  cadastrarNovo(pessoa: any): any {
    return this.http.post(environment.api_endpoint, pessoa);
  }

}
