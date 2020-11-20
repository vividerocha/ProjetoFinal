import { Component, OnInit } from '@angular/core';
import { EquipamentosReparo } from './equipamentosReparo';
import { QuadroTecnicoService } from './quadro-tecnico.service';
import { SituacaoEquipamentoService } from './../cadastro-situacao-equipamento/situacaoEquipamento.service';
import { EquipamentoService } from './../cadastro-de-equipamento/cadastro-de-equipamento.service';
import { Situacao } from '../cadastro-situacao-equipamento/situacao';
import { Equipamento } from '../cadastro-de-equipamento/equipamento';
import { HistoricoEquipamento } from '../cadastro-de-equipamento/historicoEquipamento';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-quadro-tecnico',
  templateUrl: './quadro-tecnico.component.html',
  styleUrls: ['./quadro-tecnico.component.css']
})
export class QuadroTecnicoComponent implements OnInit {

  elementos: EquipamentosReparo;
  ultimaSituacao: number;
  situacaoDescricao: string;
  situacaoEqui: Situacao;
  equipamento: Equipamento;
  equipamentoD: Equipamento;
  idUsuario: Number;
  reserva: boolean;
  confirma: boolean;
  disponibilizar: boolean;
  semAcao: boolean;

  constructor(private service: QuadroTecnicoService, private serviceSituacao: SituacaoEquipamentoService,
    private equipamentoService: EquipamentoService, private toastService: ToastrService) { }

  ngOnInit(): void {
    this.buscaEquipamentosParaReparo();    
  }

  buscaEquipamentosParaReparo(){
      this.service.getEquipamentos(parseInt(sessionStorage.getItem('idUserLogado')))
      .subscribe(res => {
        this.elementos = res;        
      }, err => {
        console.log(err);
      });
      
  }

  detalhaEquipamento(id: string){
    Array.from(document.getElementsByClassName("detalhaEquipamento")).forEach(
      div => (div.setAttribute("hidden", "true"))
    );

    if(document.getElementById(id).hidden == true){
      document.getElementById(id).hidden = false;
    }else{
      document.getElementById(id).hidden = true;
    }


    this.service.getUltimoHistorico(parseInt(id)).subscribe(cons => {
      this.ultimaSituacao = cons;
      
      this.serviceSituacao.getSituacao(cons).subscribe(desc => {
        this.situacaoDescricao = desc;
      });
      

      switch(this.ultimaSituacao){
        case 1:
          this.reserva = true;
          this.confirma = false;
          this.disponibilizar = false;
          this.semAcao = false;
          break;
        case 2:
          this.confirma = true;
          this.reserva = false;
          this.disponibilizar = false;
          this.semAcao = false;
          break;
        case 3:
          this.disponibilizar = true;
          this.reserva = false;
          this.confirma = false;
          this.semAcao = false;
          break;
        case 4:
        case 5:
        case 6:
          this.disponibilizar = false;
          this.reserva = false;
          this.confirma = false;
          this.semAcao = true;
          break;
      }
      
    });
  }

  alterarHistorico(id: number, idEquipamento: number){

    
      this.equipamentoService.getEquipamento(idEquipamento).subscribe(equi =>{
        this.equipamentoD = equi;
      });
    setTimeout(() => {
      this.idUsuario = parseInt(sessionStorage.getItem('idUserLogado'));
      //consulta objeto Situacao
      this.equipamentoService.getSituacoes(id).subscribe(res => {
        this.situacaoEqui = res;
        const dados = {
          equipamento: this.equipamentoD,
          situacao: this.situacaoEqui,
          idUsuario: this.idUsuario
        } as HistoricoEquipamento;
  
        this.equipamentoService.salvarHistorico(dados)
            .subscribe(
              response => {
                this.showSuccess("Ação realizada com Sucesso!");
                Array.from(document.getElementsByClassName("detalhaEquipamento")).forEach(
                  div => (div.setAttribute("hidden", "true"))
                );
              },
              error => {
                console.log(error);
              });
      });
    }, 120);

    
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

}
