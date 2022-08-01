import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {LoginResponse} from "./shared/login.response.model";
import {Observable} from "rxjs";

const ACCESS_TOKEN: string = 'access_token';
const REFRESH_TOKEN: string = 'refresh_token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private accessToken: string;
  private refreshToken: string;
  private roles: string[] = [];

  constructor(private http: HttpClient) {
    this.accessToken = this.getToken() ?? '';
    this.refreshToken = this.getRefreshToken() ?? '';
  }

  isTokenPresent(): boolean {
    return !!localStorage.getItem(ACCESS_TOKEN); // !! converts to boolean
  }

  getToken(): string | null {
    return this.isTokenPresent() ? localStorage.getItem(ACCESS_TOKEN) : null;
  }

  getRefreshToken(): string | null {
    return this.isTokenPresent() ? localStorage.getItem(REFRESH_TOKEN) : null;
  }

  setAccessToken(token: string): void {
    this.accessToken = token;
    localStorage.setItem(ACCESS_TOKEN, token);
  }

  setRefreshToken(refreshToken: string): void {
    this.refreshToken = refreshToken;
    localStorage.setItem(REFRESH_TOKEN, refreshToken);
  }

  setRoles(roles: string[]): void {
    this.roles = roles;
  }

  removeTokens(): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
  }

  validateToken(): Observable<boolean> {
    console.log("validateToken");
    return this.http.get<boolean>(environment.apiUrl + '/auth/validate');
  }

  refreshTheToken(): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(environment.apiUrl + '/auth/refresh', {refreshToken: this.refreshToken});
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(
      environment.apiUrl + '/auth/login', {'username': username, 'password': password});
  }

  forgot(email: string): Observable<string> {
    return this.http.post<string>(environment.apiUrl + '/auth/forgot', email);
  }

}
