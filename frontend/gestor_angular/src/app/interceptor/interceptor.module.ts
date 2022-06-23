import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InterceptorService} from "./interceptor.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";



@NgModule({
  providers: [
    InterceptorService, {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class InterceptorModule { }
