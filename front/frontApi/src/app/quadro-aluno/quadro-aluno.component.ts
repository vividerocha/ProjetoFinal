import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-quadro-aluno',
  templateUrl: './quadro-aluno.component.html',
  styleUrls: ['./quadro-aluno.component.css']
})
export class QuadroAlunoComponent implements OnInit {

  questionarioOn: boolean;

  constructor() { }

  ngOnInit(): void {
    this.verificaQuestionario();
  }

  verificaQuestionario(){
    //verifica se o aluno já preencheu o questionário, se preencheu não exibe o questionário
    this.questionarioOn = true;
  }

}
