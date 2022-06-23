import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http'
import {Injectable} from '@angular/core'
import {Router} from '@angular/router'
import {throwError} from 'rxjs'
import {catchError, map} from 'rxjs/operators'

import {AuthGuard} from './auth-guard.service'
import {AuthenticationService} from './authentication.service'

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private authGuard: AuthGuard
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): any {
    const token = this.authService.getToken();
    const refreshToken = this.authService.getRefreshToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          'Authorization': 'Bearer ' + token,
        }
      });
    }
    if (!request.headers.has('Content-Type')) {
      request = request.clone({
        setHeaders: {
          'content-type': 'application/json'
        }
      })
    }
    request = request.clone({
      headers: request.headers.set('Accept', 'application/json')
    });
    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        // if (event instanceof HttpResponse) {
        //   console.log('event--->>>', event);
        // }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.status == 401) {
          if (error.error.error === 'invalid_token') {
            this.authService.refreshTheToken().then(res => {
              if (res) {
                return next.handle(request);
              } else {
                this.router.navigate(['/login']);
                return throwError(() => error);
              }
            });
          } else {
            this.router.navigate(['login']).then(_ => console.log('redirect to login'));
          }
        }
        return throwError(error);
      })
    );

  }
}
