import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quadro-usuario',
  templateUrl: './quadro-usuario.component.html',
  styleUrls: ['./quadro-usuario.component.css']
})
export class QuadroUsuarioComponent implements OnInit {

  doadorOn: boolean;
  alunoOn: boolean;
  tecnicoOn: boolean;
  usuarioLogado: string;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.verificaUsuarioLogado();
  }

  verificaUsuarioLogado(){
    if(sessionStorage.getItem("idAluno") != null && sessionStorage.getItem("idAluno") != undefined){
      this.usuarioLogado = "Aluno";
    }else if (sessionStorage.getItem("idTecnico") != null && sessionStorage.getItem("idTecnico") != undefined){
      this.usuarioLogado = "Tecnico";
    }else if(sessionStorage.getItem("idDoador") != null && sessionStorage.getItem("idDoador") != undefined){
      this.usuarioLogado = "Doador";
    }else{
      this.router.navigate(['/home']);
    }
    switch (this.usuarioLogado){
      case "Doador":
        this.doadorOn = true;
        this.alunoOn = false;
        this.tecnicoOn = false;
        break;
      case "Aluno":
        this.doadorOn = false;
        this.alunoOn = true;
        this.tecnicoOn = false;
        break;
      case "Tecnico":
        this.doadorOn = false;
        this.alunoOn = false;
        this.tecnicoOn = true;
        break;
    }
  }
}
