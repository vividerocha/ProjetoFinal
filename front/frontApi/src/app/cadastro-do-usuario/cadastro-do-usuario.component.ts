import { Component, OnInit } from '@angular/core';
import { CadastroDoUsuarioService } from './cadastro-do-usuario.service';

@Component({
  selector: 'app-cadastro-do-usuario',
  templateUrl: './cadastro-do-usuario.component.html',
  styleUrls: ['./cadastro-do-usuario.component.css']
})
export class CadastroDoUsuarioComponent implements OnInit {

  senha: any;
  mostraMsgErroSenha: boolean = true;
  botaoLiberado = true;

  novoUser = {
    username:'',
    email:'',
    senha:'',
    dataCad:''
  };
  
  constructor(private cadastroUsuario: CadastroDoUsuarioService) { }

  ngOnInit(): void {
  }

  adicionarUser(){
    this.cadastroUsuario.salvar(this.novoUser).subscribe(()=>{
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
