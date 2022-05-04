import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDividerModule} from '@angular/material/divider';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSelectModule} from '@angular/material/select';
import {MatSortModule} from '@angular/material/sort';
import {MatStepperModule} from '@angular/material/stepper';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {CadastroComponent} from "./cadastro/cadastro.component";
import {NgxMaskModule} from "ngx-mask";

@NgModule({
  declarations: [
    CadastroComponent
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

    // MatTabsModule,
    // MatCardModule,
    // MatStepperModule,
    // MatSelectModule,
    // MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatSelectModule,
    // MatDividerModule,
    // MatTableModule,
    // MatPaginatorModule,
    // MatAutocompleteModule,
    // RouterModule,
    // MatSortModule,
    // MatCheckboxModule,
  ]

})
export class PessoaModule { }
