import {Injectable} from "@angular/core";
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanLoad,
  Route, Router,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree
} from "@angular/router";
import {Observable} from "rxjs";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {
  private isLoggedIn: boolean = false;

  constructor(private tokenService: AuthenticationService, private router: Router) {
    this.verifyLogin()
      .then(res => this.isLoggedIn = res)
      .catch(e => console.log(e));
  }

  async verifyLogin(): Promise<boolean> {
    if (!this.tokenService.isTokenPresent())
      return false;
    else if (!await this.tokenService.validateToken()) {
      this.tokenService.removeTokens();
      return false;
    }
    return true;
  }

  canLoad(route: Route, segments: UrlSegment[]): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    throw new Error("Method not implemented.");
  }

  // @ts-ignore
  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.isLoggedIn)
      return true;
    else {
      if(await this.verifyLogin()) {
        return true;
      }
      await this.router.navigate(['/login']);
      return false;
    }
  }
}
