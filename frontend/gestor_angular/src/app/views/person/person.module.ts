import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {BrowserModule} from '@angular/platform-browser';
import {RegistrationPersonView} from "./registration-person/registration-person.view";
import {NgxMaskModule} from "ngx-mask";
import {PessoaPesquisaComponent} from './search-person/search-person.view';

@NgModule({
  declarations: [
    RegistrationPersonView,
    PessoaPesquisaComponent

  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatDatepickerModule,
    NgxMaskModule.forRoot({
      dropSpecialCharacters: false
    }),
    MatNativeDateModule,
    MatCardModule,
    MatSelectModule,
  ]

})
export class PersonModule { }
