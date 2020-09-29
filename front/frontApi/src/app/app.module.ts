import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { CadastroAlunoComponent } from './cadastro-aluno/cadastro-aluno.component';
import { FormDoadorComponent } from './pageDoador/form-doador/form-doador.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SobreProjetoComponent } from './sobre-projeto/sobre-projeto.component';
import { CadastroTecnicoComponent} from './cadastro-tecnico/cadastro-tecnico.component';
import { ContatoComponent } from './contato/contato.component';
import { LoginComponent } from './login/login.component';


const appRoutes: Routes = [
   {path: '', redirectTo: 'home', pathMatch: 'full'},
   { path : 'home', component : HomeComponent },
   { path : 'sobre-projeto', component : SobreProjetoComponent },
   { path : 'cadastro-aluno', component : CadastroAlunoComponent },
   { path : 'page-doador', component : FormDoadorComponent },
   { path : 'cadastro-tecnico', component : CadastroTecnicoComponent },
   { path : 'contato', component : ContatoComponent },
   { path : 'login', component : LoginComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    CadastroAlunoComponent,
    HomeComponent,
    SobreProjetoComponent,
    CadastroTecnicoComponent,
    ContatoComponent,
    LoginComponent
  ],
  imports: [
  BrowserModule,
  FormsModule,
  HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
