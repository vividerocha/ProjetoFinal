import { Component, OnInit } from '@angular/core';
import { Aluno } from '../cadastro-aluno/aluno';
import { EquipamentoService } from '../cadastro-de-equipamento/cadastro-de-equipamento.service';
import { Equipamento } from '../cadastro-de-equipamento/equipamento';
import { HistoricoEquipamento } from '../cadastro-de-equipamento/historicoEquipamento';
import { AlunoService } from './../cadastro-aluno/cadastro-aluno.service';
import { Situacao } from './../cadastro-situacao-equipamento/situacao';
import { ToastrService } from 'ngx-toastr';
import { QuadroTecnicoService } from '../quadro-tecnico/quadro-tecnico.service';

@Component({
  selector: 'app-quadro-aluno',
  templateUrl: './quadro-aluno.component.html',
  styleUrls: ['./quadro-aluno.component.css']
})
export class QuadroAlunoComponent implements OnInit {

  questionarioOn: boolean;
  elementos;
  elements;
  existeEquipamento: boolean;
  idEqui: number;
  aluno: Aluno;
  equipamento: Equipamento;
  situacaoEqui: Situacao;
  exibeBotao: boolean;
  ultimaSituacao: number;

  constructor(private service: AlunoService,
                      private serviceEquipamento: EquipamentoService, private toastService: ToastrService,
                      private serviceT: QuadroTecnicoService) { }

  ngOnInit(): void {
    this.verificaQuestionario();
    this.buscaEquipamentoDestinado();
  }

  verificaQuestionario(){
    //verifica se o aluno já preencheu o questionário, se preencheu não exibe o questionário
    this.questionarioOn = true;
    
  }

  buscaEquipamentoDestinado(){
    setTimeout(() => {
          this.service.getEquipamentoDestinado(parseInt(sessionStorage.getItem('idUserLogado')))
        .subscribe(res => {
          this.elementos = res;
          this.idEqui = this.elementos.idEquipamento;
                this.service.getDadosTecnico(this.idEqui)
                    .subscribe(equi => {
                      this.elements = equi;
                    }, err => {
                      console.log(err);
                    });
          this.existeEquipamento = true;
          this.buscaUltimaSituacao(this.idEqui);
        }, err => {
          this.existeEquipamento = false;
          console.log(err);
        });
        
    },30);
    this.exibeBotao = true;
  }

  confirmaRecebimento(equi: number){
    console.log(equi);
    setTimeout(() => {
      this.service.getAlunoPorIduser(parseInt(sessionStorage.getItem('idUserLogado'))).subscribe(alu => {
          this.aluno = alu;
      });
    
    },30);
    setTimeout(() => {
      this.serviceEquipamento.getEquipamento(equi).subscribe(equi => {
          this.equipamento = equi;
      });
    
    },80);

    setTimeout(() => {
      console.log(this.aluno.usuario.id)
      this.salvaHistorico(this.aluno.usuario.id, this.equipamento);
    },120);
  }

  salvaHistorico(idUsuario: number, equipamento: Equipamento){
    //consulta objeto Situacao
    this.serviceEquipamento.getSituacoes(6).subscribe(res => {
      this.situacaoEqui = res;
      const dados = {
        equipamento: equipamento,
        situacao: this.situacaoEqui,
        idUsuario: idUsuario
      } as HistoricoEquipamento;
  
      this.serviceEquipamento.salvarHistorico(dados)
          .subscribe(
            response => {            
            this.showSuccess("Ação Realizada com Sucesso!");
            this.exibeBotao = false;
            },
            error => {
              console.log(error);
            });
    });

  }

  buscaUltimaSituacao(equi: number){
    this.serviceT.getUltimoHistorico(equi).subscribe(cons => {
      this.ultimaSituacao = cons;
      if(this.ultimaSituacao == 6){
        this.exibeBotao = false;
      }
    }, erroi => {
      this.exibeBotao = true;
      console.log(erroi);
    });
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

}
