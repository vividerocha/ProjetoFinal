import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {
  usuarioLogado: string;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.usuarioLogado = localStorage.getItem('isLogado');
    console.log(this.usuarioLogado);
  }

}
