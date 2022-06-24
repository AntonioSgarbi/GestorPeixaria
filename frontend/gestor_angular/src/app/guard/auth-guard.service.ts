import {Injectable} from "@angular/core";
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanLoad,
  Route,
  Router,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree
} from "@angular/router";
import {Observable} from "rxjs";
import {AuthenticationService} from "../pages/login/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {
  constructor(private tokenService: AuthenticationService, private router: Router) {
  }

  verify(): boolean {
    if (!this.tokenService.isTokenPresent()) {
      console.log('Token not present');
      return false;
    } return true;
  }

  canLoad(route: Route, segments: UrlSegment[]): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    return true;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if(this.verify()) {
      console.log('active');
      return true
    }
    else {
      console.log('canot active_______________________________');
      return this.router.navigate(['/login']);
    }
  }

}
