import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PessoaCadastroComponent} from './pages/pessoa/cadastro/pessoa-cadastro.component';
import {NavbarComponent} from "./components/navbar/navbar.component";
import {HomeComponent} from "./pages/home/home.component";
import {ProdutoCadastroComponent} from "./pages/produto/cadastro/produto-cadastro.component";
import {PessoaPesquisaComponent} from "./pages/pessoa/pesquisa/pesquisa.component";
import {LoginComponent} from "./pages/login/login.component";
import {AuthGuard} from "./guard/auth-guard.service";
import {EstoquePesquisaComponent} from "./pages/estoque/pesquisa/estoque-pesquisa.component";
import {EstoqueEntradaComponent} from "./pages/estoque/entrada/estoque-entrada.component";
import {ProdutoPesquisaComponent} from "./pages/produto/produto-pesquisa/produto-pesquisa.component";
import {FormularioComponent} from "./components/formulario/formulario.component";

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
        },        {
          path: 'produto/pesquisa',
          component: ProdutoPesquisaComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'estoque/entrada',
          component: EstoqueEntradaComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'estoque/pesquisa',
          component: EstoquePesquisaComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'form',
          component: FormularioComponent,
        },
      ]
    }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
