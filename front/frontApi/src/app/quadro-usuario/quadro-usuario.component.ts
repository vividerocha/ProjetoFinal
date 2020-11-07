import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
    this.verificaUsuarioLogado();
  }

  verificaUsuarioLogado(){
    this.usuarioLogado = "Aluno";

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
