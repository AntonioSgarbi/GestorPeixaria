import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "./authentication.service";
import {Router} from "@angular/router";
import {throwError} from "rxjs";

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthenticationService,
              private router: Router) {

    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  async ngOnInit(): Promise<void> {
    this.authService.validateToken() ? this.router.navigate(['/']) : null;
  }


  login(): void {
    console.log('login');
    if (this.form.valid) {
      let username = this.form.get('username')!.value;
      let password = this.form.get('password')!.value;
      this.authService.login(username, password)
        .subscribe({
            next: (res) => {
              this.authService.setAccessToken(res.accessToken)
              this.authService.setRefreshToken(res.refreshToken);
              this.authService.setRoles(res.roles);
              this.router.navigate(['/']);
              console.log('not navigate');
            },
            error: (err) => {
              alert('Usuário ou Senha invalido');
              return throwError(() => 'usuário ou senha inválidos');
            }
          }
        );
    } else {
      alert('Digite seu usuário e senha!');
    }
  }

}
