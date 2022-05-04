import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './pages/pessoa/cadastro/cadastro.component';
import {NavbarComponent} from "./components/navbar/navbar.component";
import {HomeComponent} from "./pages/home/home.component";

const routes: Routes = [
  {
    path: 'login',
    component: CadastroComponent
  },
  {
    path: '',
    component: NavbarComponent,
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'pessoa/cadastro',
        component: CadastroComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
