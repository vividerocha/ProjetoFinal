import { TecnicoService } from './../cadastro-tecnico/cadastro-tecnico.service';
import { DoadoresService } from './../doadores/doadores.service';
import { AlunoService } from './../cadastro-aluno/cadastro-aluno.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuService } from './menu.service';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Doador } from '../pageDoador/form-doador/doador';
import { Tecnico } from './../cadastro-tecnico/tecnico';
import { Aluno } from './../cadastro-aluno/aluno';
import { AuthService } from './../seguranca/auth.service';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  idUsuario: number;
  myNome: String = "Seu nome aqui";
  ver: boolean = true;
  doador: Doador;
  tecnico: Tecnico;
  aluno: Aluno;
  admin: boolean;
  linkRedirectDoador: boolean = false;
  linkRedirectTec:boolean = false;
  linkRedirectAluno: boolean = false;

  constructor(private menuService: MenuService,
    private router: Router,
    private alunoService: AlunoService,
    private doadorService: DoadoresService,
    private tecnicoService: TecnicoService,
    private sair: AuthService
    ) { }
    

  ngOnInit(): void {
    if (sessionStorage.getItem('idUserLogado') != null ){
      this.idUsuario = parseInt(sessionStorage.getItem('idUserLogado'));
      this.consultaDoador(this.idUsuario)
    }else{
      this.router.navigate(['/home']);
    }
    this.admin = false;
  }

  consultaDoador(id) {
    this.doadorService.getDoadorPorIduser(id)
      .subscribe(dados => {        
        this.doador = dados;        
        this.myNome = dados.nomeCompleto;
        sessionStorage.setItem('idDoador',dados.id);
        this.linkRedirectDoador = true;
        this.ver = false;
      }, error => {
        this.consultaTecnico(id)
      })
  }

  consultaTecnico(id) {
    this.tecnicoService.getTecnicoPorIduser(id)
      .subscribe(dados => {
        this.tecnico = dados;        
        this.myNome = dados.nomeCompleto;
        sessionStorage.setItem('idTecnico',dados.id);
        this.linkRedirectTec = true;
        this.ver = false;
      }, error => {
        this.consultaAluno(id)
      })
  }

  consultaAluno(id) {
    this.alunoService.getAlunoPorIduser(id)
      .subscribe(dados => {
        this.aluno = dados;
        console.log(dados);
        sessionStorage.setItem('equipamentos', JSON.stringify(dados.equipamentoAluno));                     
        this.myNome = dados.nomeCompleto;
        sessionStorage.setItem('idAluno',dados.id);
        this.linkRedirectAluno = true;
        this.ver = false;
      }, error => {
        this.myNome = "Administrador"
        this.admin = true;
        this.ver = false;
      })
  }

  consulta

  logout() {
    sessionStorage.clear();
    location.reload();
    this.sair.logout(); 
    this.router.navigate(['/home']);   
  }

}
