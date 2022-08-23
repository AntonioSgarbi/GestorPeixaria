import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Person} from "../../../model/person.model";
import {environment} from "../../../../environments/environment";
import {Page} from "../../../model/page";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class SearchPersonService {

  constructor(private http: HttpClient, private router: Router) {
  }

  findAll(personType: string, page?: number, size?: number): Observable<Page<Person>> {
    return this.http
      .get<Page<Person>>(`${environment.apiUrl}/${personType}?page=${page ?? 0}&size=${size ?? 10}`);
  }

  edit(person: Person): void {
    this.router.navigate(['/person/edit', person]);
  }

  findAllFiltered(personType: string, page: number, size: number, filter: any) {
    return this.http
      .put<Page<Person>>(`${environment.apiUrl}/${personType}/query?page=${page}&size=${size}`, filter);
  }
}
