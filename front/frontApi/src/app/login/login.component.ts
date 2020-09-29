import { DoajuService } from './../doaju.service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  senha: any;
  mostraMsgErroSenha: boolean = true;
  botaoLiberado = true;

  novoUser = {
    username:'',
    email:'',
    senha:'',
    dataCad:''
  };
  
  constructor(private doajuService: DoajuService) { }

  ngOnInit(): void {
  }

  adicionarUser(){
    this.doajuService.adicionar(this.novoUser).subscribe(()=>{
      this.novoUser = {
        username:'',
        email:'',
        senha:'',
        dataCad:''
      }
    });
  }

  compara(digitado){
   console.log(this.senha == digitado? "igual":"diferente");
   if(this.senha == digitado){
    this.mostraMsgErroSenha = true;
    this.botaoLiberado = false;
   }else{
     this.mostraMsgErroSenha = false;
   }
  }

  capturaSenha(senha){
    this.senha = senha;
  }

}
