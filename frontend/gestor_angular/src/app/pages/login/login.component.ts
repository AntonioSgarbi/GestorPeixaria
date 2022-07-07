import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "./authentication.service";
import {Router} from "@angular/router";
import {throwError} from "rxjs";
import {AppService} from "../../app.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  formReset: FormGroup;
  isReset: boolean = false;

  constructor(private appService: AppService,
              private fb: FormBuilder,
              private authService: AuthenticationService,
              private router: Router) {

    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(5)]]
    });
    this.formReset = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  async ngOnInit(): Promise<void> {
    this.authService.validateToken() ? await this.router.navigate(['/']) : null;
  }


  login(): void {
    if (this.form.valid) {
      let username = this.form.get('username')!.value;
      let password = this.form.get('password')!.value;

      this.authService.login(username, password)
        .subscribe({
          next: (res) => {
            this.authService.setAccessToken(res.accessToken)
            this.authService.setRefreshToken(res.refreshToken);
            this.authService.setRoles(res.roles);
            this.appService.showMessage('Sucesso!', 'fechar');
            this.router.navigate(['/']);
          },
          error: (err) => {
            console.log(err)
            this.appService.showMessage('Usuário ou Senha invalido!', 'fechar');
            return throwError(() => 'usuário ou senha inválidos');
          },
          complete: () => {
            console.log('complete')
          }
        });
    } else {
      this.appService.showMessage('Digite seu usuário e senha!', 'fechar');
    }
  }

  changeToReset() {
    this.isReset = true;
  }

  resetPassword() {
    if (this.formReset.valid) {
      console.log('valid')
      let email: string = this.formReset.get('email')!.value;
      this.authService.forgot(email)
        .subscribe({
          next: () => {
            this.appService.showMessage('Informações de acesso enviadas!', 'fechar')
            this.isReset = false;
          },
          error: (err) => {
            this.appService.showMessage('Falha ao gerar credenciais', 'fechar')
          },
          complete: () => {
            this.isReset = false;
          }
        });
    } else {
      console.log(this.formReset)
      this.appService.showMessage('invalido!', 'fechar')
    }

  }
}
