import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

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
import { CadastroDoUsuarioComponent } from './cadastro-do-usuario/cadastro-do-usuario.component';
import { CommonModule } from '@angular/common';
import { CadastroDeEquipamentoComponent } from './cadastro-de-equipamento/cadastro-de-equipamento.component';
import { AdminComponent } from './admin/admin.component';
import { CadastroTipoEquipamentoComponent } from './cadastro-tipo-equipamento/cadastro-tipo-equipamento.component';
import { CadastroSituacaoEquipamentoComponent } from './cadastro-situacao-equipamento/cadastro-situacao-equipamento.component';
import { RankingComponent } from './ranking/ranking.component';
import { MensagemComponent } from './mensagem/mensagem.component';
import { DoadoresComponent } from './doadores/doadores.component';
import { TecnicosComponent } from './tecnicos/tecnicos.component';
import { AlunosComponent } from './alunos/alunos.component';
import { SegurancaComponent } from './seguranca/seguranca.component';

import { AuthService } from './seguranca/auth.service';
import { MatTableModule } from '@angular/material/table';
import { QuadroUsuarioComponent } from './quadro-usuario/quadro-usuario.component';
import { QuadroAlunoComponent } from './quadro-aluno/quadro-aluno.component';
import { QuadroTecnicoComponent } from './quadro-tecnico/quadro-tecnico.component';
import { QuestionarioComponent } from './questionario/questionario.component';




const appRoutes: Routes = [
   {path: '', redirectTo: 'home', pathMatch: 'full'},
   { path : 'home', component : HomeComponent },   
   { path : 'sobre-projeto', component : SobreProjetoComponent },
   { path : 'cadastro-aluno', component : CadastroAlunoComponent },
   { path : 'page-doador', component : FormDoadorComponent },
   { path : 'cadastro-tecnico', component : CadastroTecnicoComponent },
   { path : 'contato', component : ContatoComponent },
   { path : 'login', component : LoginComponent },
   { path : 'cadastro-usuario', component : CadastroDoUsuarioComponent},
   { path : 'cadastro-equipamento', component : CadastroDeEquipamentoComponent},
   { path : 'admin', component : AdminComponent},
   { path : 'quadro', component : QuadroUsuarioComponent}
   
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
    LoginComponent,
    CadastroDoUsuarioComponent,
    CadastroDeEquipamentoComponent,
    FormDoadorComponent,
    AdminComponent,
    CadastroTipoEquipamentoComponent,
    CadastroSituacaoEquipamentoComponent,
    RankingComponent,
    MensagemComponent,
    DoadoresComponent,
    TecnicosComponent,
    AlunosComponent,
    SegurancaComponent,
    QuadroUsuarioComponent,
    QuadroAlunoComponent,
    QuadroTecnicoComponent,
    QuestionarioComponent
  
  ],
  imports: [

  ToastrModule.forRoot({
    timeOut: 10000,
    positionClass: 'toast-top-center'
  }),
  BrowserModule,
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  MatTableModule,
  HttpClientModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule
  ],
  providers: [AuthService],


  bootstrap: [AppComponent]
  
})
export class AppModule { }
