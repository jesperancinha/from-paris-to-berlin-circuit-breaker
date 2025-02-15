import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FptbOverviewComponent} from './fptb-overview/fptb-overview.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DiagramComponent} from "gojs-angular";
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";
import {MAT_DIALOG_DEFAULT_OPTIONS} from "@angular/material/dialog";
import {LocationStrategy, PathLocationStrategy} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {MatListModule} from "@angular/material/list";

@NgModule({
  declarations: [
    AppComponent,
    FptbOverviewComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        DiagramComponent,
        MatCardModule,
        MatTableModule,
        HttpClientModule,
        MatListModule
    ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: PathLocationStrategy,
    }, {
      provide: MAT_DIALOG_DEFAULT_OPTIONS,
      useValue: {hasBackdrop: true}
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
