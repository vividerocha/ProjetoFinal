import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl,  NgForm, Validators } from '@angular/forms';
import { SituacaoEquipamentoService } from './situacaoEquipamento.service';
import { ToastrService } from 'ngx-toastr';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { Situacao } from './situacao';

@Component({
  selector: 'app-cadastro-situacao-equipamento',
  templateUrl: './cadastro-situacao-equipamento.component.html',
  styleUrls: ['./cadastro-situacao-equipamento.component.css']
})
export class CadastroSituacaoEquipamentoComponent implements OnInit {

  form: FormGroup;
  formDetalheTipo: FormGroup;
  situacaoEquipamento: Situacao;
  formularioInvalido: boolean;
  escondeMsgErroTipo: boolean;
  habilita: boolean;
  displayedColumns: string[] = ['idTipo', 'situacao', '-Tipo'];
  dataSourceTipo;
  elementsTipo;
  confirmaExclusao: boolean;

  constructor(
    private fb: FormBuilder,
    private service: SituacaoEquipamentoService,
    private toastService: ToastrService) { }

  ngOnInit(): void {

    this.formularioInvalido = false;
    this.escondeMsgErroTipo = true;
    this.habilita = false;
    this.confirmaExclusao = true;
    this.createFormTipo(); 
    this.carregaLista();
    this.detalhaItemTipo(0);
 
  }

  createFormTipo() {
    this.form = this.fb.group({
      situacao: new FormControl(null, [Validators.required, Validators.minLength(4)])
    });

    this.formDetalheTipo = this.fb.group({
      id: new FormControl(''),
      situacao: new FormControl(null, [Validators.required, Validators.minLength(4)])
    });
  }

  onSubmit(form: NgForm){
    
    if(this.form.valid){
      this.service.salvar(this.form.value).subscribe(() => {
        this.toastService.success("Cadastro realizado com Sucesso!");
        this.carregaLista();
        this.form.get('descricao').setValue('');
        this.escondeMsgErroTipo = true;

      });
    }else{
      this.formularioInvalido = true;
      this.escondeMsgErroTipo = false;
    }  
  }

  carregaLista(){
    this.service.getSituacoesEquipamentos()
    .subscribe(res => {
      this.elementsTipo = res;
      this.dataSourceTipo = new MatTableDataSource(res);
      this.escondeMsgErroTipo = true;
    }, err => {
      console.log(err);
    });
  }

  verificaSituacao(digitado){
    this.service.verificaSituacao(digitado)
      .subscribe(dados => {
        this.escondeMsgErroTipo = false;
        this.habilita = true;
      },
        error => {
          console.log(error);
          this.escondeMsgErroTipo = true;
          this.habilita = false;
        });
  }

  get situacao() {
    return this.form.get('situacao');
  }

  get situacaoD() {
    return this.formDetalheTipo.get('situacao');
  }

  showSucesso(mensagem: string){
    this.toastService.success(mensagem);
  }

  detalhaItemTipo(id: number){
    if(id != 0){
      this.formDetalheTipo.get('id').setValue(id);
      this.service.getSituacao(id).subscribe(res => {
        this.situacaoEquipamento = res;
        this.escondeMsgErroTipo = true;
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
    this.service.delete(id)
      .subscribe(
        response => {
          this.showSucesso("Cadastro excluído com Sucesso!");
          this.carregaLista();
        },
        error => {
          console.log(error);
        });
  }

  atualiza(form: NgForm){
    if(this.formDetalheTipo.valid){
      let id = this.formDetalheTipo.get('id').value;
      console.log(this.formDetalheTipo.value);
      this.service.atualizar(id, this.formDetalheTipo.value).subscribe(() => {      
        this.showSucesso("Cadastro alterado com Sucesso!");
        this.carregaLista();
      });
    }else{
      this.formularioInvalido = true;
    } 
  }

}
