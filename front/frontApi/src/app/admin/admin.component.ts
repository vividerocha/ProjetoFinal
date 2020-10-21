import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  TipoEquipamento: boolean;
  SituacaoEquipamento: boolean;
  Doadores: boolean;
  Tecnicos: boolean;
  Alunos: boolean;
  Ranking: boolean;
  Mensagens: boolean;
  classeTipo: string;
  classeSituacao: string;
  classeDoadores: string;
  classeTecnicos: string;
  classeAlunos: string;
  classeRanking: string;
  classeMensagens: string;

  constructor() { }

  ngOnInit(): void {
    this.TipoEquipamento = false;
    this.SituacaoEquipamento = true;
    this.Doadores = true;
    this.Tecnicos = true;
    this.Alunos = true;
    this.Ranking = true;
    this.Mensagens = true;
    this.classeTipo = "nav-link active";
    this.classeSituacao = "nav-link";
    this.classeDoadores = "nav-link";
    this.classeTecnicos = "nav-link";
    this.classeAlunos = "nav-link";
    this.classeRanking = "nav-link";
    this.classeMensagens = "nav-link";
  }

  exibeDiv(nomeDiv: string){
     switch(nomeDiv) { 
        case "TipoEquipamento": { 
          this.TipoEquipamento = false;
          this.SituacaoEquipamento = true;
          this.Doadores = true;
          this.Tecnicos = true;
          this.Alunos = true;
          this.Ranking = true;
          this.Mensagens = true;
          this.classeTipo = "nav-link active";
          this.classeSituacao = "nav-link";
          this.classeDoadores = "nav-link";
          this.classeTecnicos = "nav-link";
          this.classeAlunos = "nav-link";
          this.classeRanking = "nav-link";
          this.classeMensagens = "nav-link";
          break; 
        } 
        case "SituacaoEquipamento": { 
          this.TipoEquipamento = true;
          this.SituacaoEquipamento = false;
          this.Doadores = true;
          this.Tecnicos = true;
          this.Alunos = true;
          this.Ranking = true;
          this.Mensagens = true;
          this.classeTipo = "nav-link";
          this.classeSituacao = "nav-link active";
          this.classeDoadores = "nav-link";
          this.classeTecnicos = "nav-link";
          this.classeAlunos = "nav-link";
          this.classeRanking = "nav-link";
          this.classeMensagens = "nav-link";
          break; 
        } 
        case "Doadores": { 
          this.TipoEquipamento = true;
          this.SituacaoEquipamento = true;
          this.Doadores = false;
          this.Tecnicos = true;
          this.Alunos = true;
          this.Ranking = true;
          this.Mensagens = true;
          this.classeTipo = "nav-link";
          this.classeSituacao = "nav-link";
          this.classeDoadores = "nav-link active";
          this.classeTecnicos = "nav-link";
          this.classeAlunos = "nav-link";
          this.classeRanking = "nav-link";
          this.classeMensagens = "nav-link";
          break; 
        }
        case "Tecnicos": { 
          this.TipoEquipamento = true;
          this.SituacaoEquipamento = true;
          this.Doadores = true;
          this.Tecnicos = false;
          this.Alunos = true;
          this.Ranking = true;
          this.Mensagens = true; 
          this.classeTipo = "nav-link";
          this.classeSituacao = "nav-link";
          this.classeDoadores = "nav-link";
          this.classeTecnicos = "nav-link active";
          this.classeAlunos = "nav-link";
          this.classeRanking = "nav-link";
          this.classeMensagens = "nav-link";
          break; 
        }
        case "Alunos": { 
          this.TipoEquipamento = true;
          this.SituacaoEquipamento = true;
          this.Doadores = true;
          this.Tecnicos = true;
          this.Alunos = false;
          this.Ranking = true;
          this.Mensagens = true;
          this.classeTipo = "nav-link";
          this.classeSituacao = "nav-link";
          this.classeDoadores = "nav-link";
          this.classeTecnicos = "nav-link";
          this.classeAlunos = "nav-link active";
          this.classeRanking = "nav-link";
          this.classeMensagens = "nav-link";
          break; 
        }
        case "Ranking": { 
          this.TipoEquipamento = true;
          this.SituacaoEquipamento = true;
          this.Doadores = true;
          this.Tecnicos = true;
          this.Alunos = true;
          this.Ranking = false;
          this.Mensagens = true; 
          this.classeTipo = "nav-link";
          this.classeSituacao = "nav-link";
          this.classeDoadores = "nav-link";
          this.classeTecnicos = "nav-link";
          this.classeAlunos = "nav-link";
          this.classeRanking = "nav-link active";
          this.classeMensagens = "nav-link";
          break; 
        }
        case "Mensagens": { 
          this.TipoEquipamento = true;
          this.SituacaoEquipamento = true;
          this.Doadores = true;
          this.Tecnicos = true;
          this.Alunos = true;
          this.Ranking = true;
          this.Mensagens = false;
          this.classeTipo = "nav-link";
          this.classeSituacao = "nav-link";
          this.classeDoadores = "nav-link";
          this.classeTecnicos = "nav-link";
          this.classeAlunos = "nav-link";
          this.classeRanking = "nav-link";
          this.classeMensagens = "nav-link active"; 
          break; 
        } 
        default: { 
          //statements; 
          break; 
        } 
    } 
  }

}
