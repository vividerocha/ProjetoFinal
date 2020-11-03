import { Component, OnInit, ViewChild  } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { EquipamentoService } from './cadastro-de-equipamento.service';
import { Equipamento } from './equipamento';
import { ToastrService } from 'ngx-toastr';
import { Session } from 'protractor';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { TipoEquipamento } from './../cadastro-tipo-equipamento/tipoEquipamento';
import { HistoricoEquipamento } from './historicoEquipamento';
import { Situacao } from './../cadastro-situacao-equipamento/situacao';


@Component({
  selector: 'app-cadastro-de-equipamento',
  templateUrl: './cadastro-de-equipamento.component.html',
  styleUrls: ['./cadastro-de-equipamento.component.css']
})
export class CadastroDeEquipamentoComponent implements OnInit {

  displayedColumns: string[] = ['id','tipoEquipamento', 'descricao', 'funcionando',  '-'];
  tiposEquipamentosLista: TipoEquipamento;
  tipoEqui: TipoEquipamento;
  situacaoEqui: Situacao;
  formEquipamento: FormGroup;
  formDetalhe: FormGroup;
  formularioInvalido: boolean;
  equipamento: Equipamento;
  dataSource;
  elements;
  confirmaExclusao: boolean;
  //idDoador = sessionStorage.getItem("idUser");
  idDoador = 1;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private equipamentoService: EquipamentoService,
    private fb: FormBuilder, private toastService: ToastrService) { }

  ngOnInit(): void {
    this.criaForm();
    this.getTiposEquipamentos();
    this.carregaEquipamentos();
    this.desisteExcluir();
  }

  getTiposEquipamentos() {
    this.equipamentoService.getTiposEquipamentos().subscribe(data => {
      this.tiposEquipamentosLista = data;
    });
  }
  criaForm(){
    this.formEquipamento = this.fb.group({
      tiposEquipamentos : new FormControl(''),
      funcionando: new FormControl(''),
      descricaoEquipamento: new FormControl(null, [Validators.required])
    });

    this.formDetalhe = this.fb.group({
      id: new FormControl(''),
      tiposEquipamentos : new FormControl(''),
      funcionando: [''],
      descricaoEquipamento: new FormControl(null, [Validators.required])
    });
  }

  onSubmit(form: NgForm){
    let func;
    if(this.formEquipamento.value.funcionando == "true"){
      func = true;
    }else{
      func = false;
    }

    console.log(this.formEquipamento.value.tiposEquipamentos);
    
    const dados = {
      descricaoEquipamento: this.formEquipamento.value.descricaoEquipamento,
      funcionando: func,
      tipoEquipamento: this.formEquipamento.value.tiposEquipamentos
    } as Equipamento;

    console.log(dados);
    if(this.formEquipamento.valid){
        this.equipamentoService.salvar(dados)
        .subscribe(
          response => {
            console.log(response);            
            this.showSuccess("Cadastro realizado com Sucesso!");
            this.salvaHistorico(this.idDoador, response);
            this.formEquipamento.reset();
            this.carregaEquipamentos();
          },
          error => {
            console.log(error);
            alert(error.error);
          });
    }else{
      this.formularioInvalido = true;
    }
  }

  salvaHistorico(idUsuario: number, equipamento: Equipamento){
    //consulta objeto Situacao
    this.equipamentoService.getSituacoes(1).subscribe(res => {
      this.situacaoEqui = res;
      const dados = {
        equipamento: equipamento,
        situacao: this.situacaoEqui,
        idUsuario: idUsuario
      } as HistoricoEquipamento;
  
      console.log(dados);
      this.equipamentoService.salvarHistorico(dados)
          .subscribe(
            response => {
              console.log(response);
            },
            error => {
              console.log(error);
              alert(error.error);
            });
    });
    
  }


  get funcionando(){
    return this.formEquipamento.get('funcionando');
  }

  get descricaoEquipamento(){
    return this.formEquipamento.get('descricaoEquipamento');
  }

  showSuccess(mensagem: string) {
      this.toastService.success(mensagem);
  }

  carregaEquipamentos() {
    this.equipamentoService.getEquipamentos()
    .subscribe(res => {
      this.elements = res;
      this.dataSource = new MatTableDataSource(res);
      //this.isLoadingResults = false;
    }, err => {
      console.log(err);
      //this.isLoadingResults = false;
    });
  }

  detalhaItem(id: number){
    if(id != 0){
      this.formDetalhe.get('id').setValue(id);
      this.equipamentoService.getEquipamento(id).subscribe(res => {
        this.equipamento = res;
        this.formDetalhe.get("funcionando").setValue(this.equipamento.funcionando);    
        this.formDetalhe.get("tiposEquipamentos").setValue(this.equipamento.tipoEquipamento.id)
      }
      );
    }
  }
  confirmaExcluir(){
    this.confirmaExclusao = false;
  }

  desisteExcluir(){
    this.confirmaExclusao = true;
  }

  excluir(id: number): void {
    this.equipamentoService.delete(id)
      .subscribe(
        response => {
          this.showSuccess("Cadastro excluído com Sucesso!");
          this.carregaEquipamentos();
          this.desisteExcluir();
        },
        error => {
          console.log(error);
        });
  }

  atualizar(form: NgForm){
    this.atribuiObjeto();
    setTimeout(() => {
      if(this.tipoEqui != null || this.tipoEqui != undefined){
        const dados = {
          id: this.formDetalhe.get('id').value,
          descricaoEquipamento: this.formDetalhe.get('descricaoEquipamento').value,
          funcionando: this.formDetalhe.get('funcionando').value,
          tipoEquipamento: this.tipoEqui
        } as Equipamento;
    
        console.log(dados);
        if(this.formDetalhe.valid){
          let id = this.formDetalhe.get('id').value;
          this.equipamentoService.atualizar(id, dados).subscribe(() => {      
            this.showSuccess("Cadastro alterado com Sucesso!");
            this.carregaEquipamentos();
          });
        }else{
          this.formularioInvalido = true;
        } 
      }
      
    }, 60);

    
  }

  atribuiObjeto(){
    this.retornaTipoEquipamento(this.formDetalhe.value.tiposEquipamentos);
  }

  retornaTipoEquipamento(id: number){
    this.equipamentoService.getTipoEquipamento(id).subscribe(
      (res) => {
         this.tipoEqui = res;
      },
      (error) => {
         console.error(error)
      }
    );
  }
}