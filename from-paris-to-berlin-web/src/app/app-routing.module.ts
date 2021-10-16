import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FptbOverviewComponent} from "./fptb-overview/fptb-overview.component";

const routes: Routes = [
  {path: '', redirectTo: '/main', pathMatch: 'full'},
  {path: 'main', component: FptbOverviewComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
