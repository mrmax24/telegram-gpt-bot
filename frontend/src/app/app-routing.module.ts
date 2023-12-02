import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import { AuthComponent } from './auth/auth.component';
import {AuthGuard} from "./auth.guard";
import { RegisterComponent } from './register/register.component';
import { PanelComponent } from './panel/panel.component';
import { LogsComponent } from './logs/logs.component';


const routes: Routes = [
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/login', component: AuthComponent },
  { path: '', component: PanelComponent, canActivate: [AuthGuard] },
  { path: 'logs/:chatId', component: LogsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [],
})
export class AppRoutingModule { }
