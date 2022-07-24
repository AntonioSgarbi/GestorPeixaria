import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationPersonView} from './views/person/registration-person/registration-person.view';
import {NavbarComponent} from "./components/navbar/navbar.component";
import {HomeComponent} from "./views/home/home.component";
import {RegistrationProductView} from "./views/product/registration-product/registration-product.view";
import {PessoaPesquisaComponent} from "./views/person/search-person/search-person.view";
import {LoginView} from "./views/login/login.view";
import {AuthGuard} from "./guard/auth-guard.service";
import {SearchStockView} from "./views/stock/search-stock/search-stock.view";
import {EntryStockView} from "./views/stock/entry-stock/entry-stock.view";
import {SearchProductView} from "./views/product/search-product/search-product.view";

const routes: Routes = [
    {
      path: 'login',
      component: LoginView,
    },
    {
      path: '',
      component: NavbarComponent,
      canActivate: [AuthGuard],
      children: [
        {
          path: '',
          component: HomeComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'person/registration-person',
          component: RegistrationPersonView,
          canActivate: [AuthGuard],
        },
        {
          path: 'person/search-person',
          component: PessoaPesquisaComponent,
          canActivate: [AuthGuard],
        },
        {
          path: 'product/registration-product',
          component: RegistrationProductView,
          canActivate: [AuthGuard],
        },        {
          path: 'product/search-product',
          component: SearchProductView,
          canActivate: [AuthGuard],
        },
        {
          path: 'stock/entry-stock',
          component: EntryStockView,
          canActivate: [AuthGuard],
        },
        {
          path: 'stock/search-stock',
          component: SearchStockView,
          canActivate: [AuthGuard],
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
