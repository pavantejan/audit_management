import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckListComponent } from './check-list/check-list.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { LoginComponent } from './login/login.component';
import { SeverityComponent } from './severity/severity.component';
import { ResponsesComponent } from './responses/responses.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  {path:"welcomepage",component:WelcomeComponent},
  {path:"login",component:LoginComponent},
  {path:"checklist",component:CheckListComponent},
  {path:"severity",component:SeverityComponent},
  {path:"responses",component:ResponsesComponent},
  {path:"error",component:ErrorPageComponent},
  {path:"**",redirectTo:"/welcomepage"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
