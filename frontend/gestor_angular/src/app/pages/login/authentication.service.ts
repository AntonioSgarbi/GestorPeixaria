import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {LoginResponse} from "./shared/login.response.model";
import {Observable, tap, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

const ACCESS_TOKEN: string = 'access_token';
const REFRESH_TOKEN: string = 'refresh_token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private accessToken: string;
  private refreshToken: string;
  private loginResponse: LoginResponse | null = null;

  constructor(private http: HttpClient) {
    this.accessToken = this.getToken() ?? '';
    this.refreshToken = this.getRefreshToken() ?? '';
  }

  isTokenPresent(): boolean {
    return !!localStorage.getItem(ACCESS_TOKEN);
  }

  getToken(): string | null {
    return this.isTokenPresent() ? localStorage.getItem(ACCESS_TOKEN) : null;
  }

  getRefreshToken(): string | null {
    return this.isTokenPresent() ? localStorage.getItem(REFRESH_TOKEN) : null;
  }

  setAccessToken(token: string): void {
    localStorage.setItem(ACCESS_TOKEN, token);
  }

  setRefreshToken(refreshToken: string): void {
    localStorage.setItem(REFRESH_TOKEN, refreshToken);
  }

  removeTokens(): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
  }

  async validateToken(): Promise<boolean> {
    let isValid = false;
    this.http.get(environment.apiUrl + '/auth/validate')
      .subscribe({
        next: () => {
          isValid = true;
        },
        error: () => {
          console.log('tokenExpirado');
          this.refreshTheToken().then(res => {
            isValid = res;
          });
        }
      });
    return isValid;
  }

  async refreshTheToken(): Promise<boolean> {
    let isRefreshed = false;
    await this.http.post(environment.apiUrl + '/auth/refresh', {refresh_token: this.refreshToken})
      .subscribe({
        next: (data: any) => {
          this.setAccessToken(data.access_token);
          this.setRefreshToken(data.refresh_token);
          isRefreshed = true;
        },
        error: () => {
          this.removeTokens();
          console.log('refreshTokenExpirado');
          isRefreshed = false;
        }
      });
    return isRefreshed;
  }

  login(username: string, password: string): Observable<any> {
    return this.http
      .post<LoginResponse>(environment.apiUrl + '/auth/login', {
      'username': username,
      'password': password
    })
      .pipe(
        tap(res => {
            this.setAccessToken(res.access_token)
            this.setRefreshToken(res.refresh_token);
          }
        ), catchError(() => {
            alert('Usuário ou Senha invalido');
            return throwError(() => 'usuário ou senha inválidos');
          }
        ));
  }

}
