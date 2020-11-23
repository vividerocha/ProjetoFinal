import { Component, OnInit } from '@angular/core';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { RankingService } from './ranking.service';
import { FormGroup, FormBuilder, FormControl, NgForm } from '@angular/forms';
import { AlunoService } from './../cadastro-aluno/cadastro-aluno.service';
import { Aluno } from './../cadastro-aluno/aluno';
import { EquipamentoService } from '../cadastro-de-equipamento/cadastro-de-equipamento.service';
import { Equipamento } from './../cadastro-de-equipamento/equipamento';
import { Situacao } from './../cadastro-situacao-equipamento/situacao';
import { HistoricoEquipamento } from '../cadastro-de-equipamento/historicoEquipamento';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {
  
  detalhaS: boolean;
  dataSource;
  elements;
  equipamentos;
  form: FormGroup;
  resultEquipamentos: string;
  displayedColumns: string[] = ['id','regiao', 'nome', 'pontuacao'];
  aluno: Aluno;
  equipamento: Equipamento;
  situacaoEqui: Situacao;
  botaoDistribuir: boolean;

  constructor(private service: RankingService, private fb: FormBuilder,
              private serviceAluno: AlunoService, private serviceEquipamento: EquipamentoService,
              private toastService: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.detalhaS = false;
    this.criaForm();
  }

  criaForm(){
    this.form = this.fb.group({
      regiao : new FormControl('')
    });
  }

  carregaRanking() {
    let regiao = this.form.get('regiao').value;
    this.service.getRanking(regiao)
    .subscribe(res => {
      this.elements = res;
      this.dataSource = new MatTableDataSource(res);
      this.getEquipamentos(regiao);
    }, err => {
      console.log(err);
    });
  }

  getEquipamentos(regiao: string){
    this.service.getEquipamentos(regiao)
    .subscribe(equi => {
      this.equipamentos = equi;
      this.resultEquipamentos = "Existe(m) " + this.equipamentos.length + " equipamento(s) disponível(is) para Distribuição!";
      if(this.equipamentos.length == 0){
        this.botaoDistribuir = false;
      }else{
        this.botaoDistribuir = true;
      }
    }, err => {
      console.log(err);
    });
  }

  distribuirEquipamentos(){
    for (let i = 0; i < this.equipamentos.length; i++) {
      setTimeout(() => {
        this.serviceAluno.getAluno(this.elements[i].idAluno).subscribe(alu => {          
          this.serviceEquipamento.getEquipamento(this.equipamentos[i].idEquipamento).subscribe(equi => {
            this.salvaHistorico(alu.usuario.id, equi);
            });
        });
    }, 60);       
    }
  }

  salvaHistorico(idUsuario: number, equipamento: Equipamento){
    //consulta objeto Situacao
    this.serviceEquipamento.getSituacoes(5).subscribe(res => {
      this.situacaoEqui = res;
      const dados = {
        equipamento: equipamento,
        situacao: this.situacaoEqui,
        idUsuario: idUsuario
      } as HistoricoEquipamento;
  
      this.serviceEquipamento.salvarHistorico(dados)
          .subscribe(
            response => {            
            this.showSuccess("Equipamentos Distribuídos com Sucesso!");
            this.router.navigate(['/admin']);
            },
            error => {
              console.log(error);
            });
    });
    
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }
}
