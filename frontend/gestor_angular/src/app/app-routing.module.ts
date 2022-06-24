import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PessoaCadastroComponent} from './pages/pessoa/cadastro/pessoa-cadastro.component';
import {NavbarComponent} from "./components/navbar/navbar.component";
import {HomeComponent} from "./pages/home/home.component";
import {ProdutoCadastroComponent} from "./pages/produto/cadastro/produto-cadastro.component";
import {PessoaPesquisaComponent} from "./pages/pessoa/pesquisa/pesquisa.component";
import {LoginComponent} from "./pages/login/login.component";
import {AuthGuard} from "./guard/auth-guard.service";

const routes: Routes = [
    {
      path: 'login',
      component: LoginComponent,
    },
    {
      path: '',
      component: NavbarComponent,
      canActivate: [AuthGuard],
      children: [
        {
          path: 'home',
          component: HomeComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'pessoa/cadastro',
          component: PessoaCadastroComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'pessoa/pesquisa',
          component: PessoaPesquisaComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'produto/cadastro',
          component: ProdutoCadastroComponent,
          canActivate: [AuthGuard],
        }
      ]
    }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
