import { Component, OnInit } from '@angular/core';
import { MenuService } from './menu.service';
import { CommonModule } from '@angular/common';  
import { BrowserModule } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  idUsuario: string;
  logado: Boolean = false;
  nomeUsuario: string;
  tipoUsuario: string;

  constructor(private menuService: MenuService,private router: Router) { }

  ngOnInit(): void {
    this.idUsuario = sessionStorage.getItem('user');
    this.tipoUsuario = "0";
    //sessionStorage.setItem('user', null);    
  }

  consultaUsuario(){
    this.menuService.getUsuario(this.idUsuario)
    .subscribe(dados =>{
        console.log(dados);
        this.nomeUsuario = dados.nome;
        this.tipoUsuario = dados.tipoUsuario;
      },
    error => {
      console.log(error);
      this.tipoUsuario = "0";
    });
  }

  logout(){
        sessionStorage.setItem('isLogado', null);
        sessionStorage.setItem('user', null);
        this.router.navigate(['/home']);
  }

}
