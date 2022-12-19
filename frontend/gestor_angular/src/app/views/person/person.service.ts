import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Person} from "../../model/person.type";
import {RegistrationType} from "../../model/registration.type.enum";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) {
  }

  registerPerson(person: Person, type: RegistrationType): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/${type.valueOf()}`, person);
  }

  findById(id: number, type: string): Observable<Person> {
    return this.http.get(`${environment.apiUrl}/${type.valueOf()}/${id}`);
  }

}
