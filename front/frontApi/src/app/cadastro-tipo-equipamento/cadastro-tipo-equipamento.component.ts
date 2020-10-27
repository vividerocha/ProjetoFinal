import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl,  NgForm, Validators } from '@angular/forms';
import { TipoEquipamentoService } from './tipoEquipamento.service'
import { ToastrService } from 'ngx-toastr';
import { TipoEquipamento } from './tipoEquipamento';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-cadastro-tipo-equipamento',
  templateUrl: './cadastro-tipo-equipamento.component.html',
  styleUrls: ['./cadastro-tipo-equipamento.component.css']
})
export class CadastroTipoEquipamentoComponent implements OnInit {

  form: FormGroup;
  formDetalheTipo: FormGroup;
  tipoEquipamento: TipoEquipamento;
  formularioInvalido: boolean;
  escondeMsgErroTipo: boolean;
  habilita: boolean;
  displayedColumns: string[] = ['idTipo', 'descricaoTipo', '-Tipo'];
  dataSourceTipo;
  elementsTipo;
  confirmaExclusao: boolean;

  constructor(
    private fb: FormBuilder,
    private tipoEquipamentoService: TipoEquipamentoService,
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
      descricao: new FormControl(null, [Validators.required, Validators.minLength(4)])
    });

    this.formDetalheTipo = this.fb.group({
      id: new FormControl(''),
      descricao: new FormControl(null, [Validators.required, Validators.minLength(4)])
    });
  }

  onSubmit(form: NgForm){
    
    if(this.form.valid){
      this.tipoEquipamentoService.salvar(this.form.value).subscribe(() => {
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
    this.tipoEquipamentoService.getTiposEquipamentos()
    .subscribe(res => {
      this.elementsTipo = res;
      this.dataSourceTipo = new MatTableDataSource(res);
      this.escondeMsgErroTipo = true;
    }, err => {
      console.log(err);
    });
  }

  verificaTipo(digitado){
    this.tipoEquipamentoService.verificaTipo(digitado)
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

  get descricao() {
    return this.form.get('descricao');
  }

  get descricaoD() {
    return this.formDetalheTipo.get('descricao');
  }

  showSucesso(mensagem: string){
    this.toastService.success(mensagem);
  }

  detalhaItemTipo(id: number){
    if(id != 0){
      this.formDetalheTipo.get('id').setValue(id);
      this.tipoEquipamentoService.getTipo(id).subscribe(res => {
        this.tipoEquipamento = res;
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
    this.tipoEquipamentoService.delete(id)
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
      this.tipoEquipamentoService.atualizar(id, this.formDetalheTipo.value).subscribe(() => {      
        this.showSucesso("Cadastro alterado com Sucesso!");
        this.carregaLista();
      });
    }else{
      this.formularioInvalido = true;
    } 
  }
  
}
