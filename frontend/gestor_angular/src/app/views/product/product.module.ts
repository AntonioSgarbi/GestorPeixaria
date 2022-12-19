import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegistrationProductView} from './registration-product/registration-product.view';
import {MatCardModule} from "@angular/material/card";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {SearchProductView} from './search-product/search-product.view';
import {SearchBarModule} from "../../components/search-bar/search-bar.module";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    RegistrationProductView,
    SearchProductView
  ],
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatInputModule,
        MatFormFieldModule,
        MatCardModule,
        MatSelectModule,
        MatButtonModule,
        SearchBarModule,
        MatSidenavModule,
        MatIconModule,
        MatTableModule,
        MatPaginatorModule,
        MatExpansionModule,
        MatProgressSpinnerModule,
        RouterModule
    ]
})
export class ProductModule {
}
