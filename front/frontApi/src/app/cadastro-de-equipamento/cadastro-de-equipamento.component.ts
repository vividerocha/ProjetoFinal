import { Component, OnInit, ViewChild  } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { EquipamentoService } from './cadastro-de-equipamento.service';
import { Equipamento } from './equipamento';
import { TipoEquipamento } from '../cadastro-tipo-equipamento/tipoEquipamento';
import { ToastrService } from 'ngx-toastr';
import { Session } from 'protractor';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cadastro-de-equipamento',
  templateUrl: './cadastro-de-equipamento.component.html',
  styleUrls: ['./cadastro-de-equipamento.component.css']
})
export class CadastroDeEquipamentoComponent implements OnInit {

  displayedColumns: string[] = ['id', 'descricao', 'funcionando',  '-'];
  tiposEquipamentosLista: TipoEquipamento;
  formEquipamento: FormGroup;
  formularioInvalido: boolean;
  dataSource;
  elements;
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

    //console.log(this.formEquipamento.value);
    console.log(dados);
    if(this.formEquipamento.valid){
        this.equipamentoService.salvar(dados)
        //this.equipamentoService.salvar(this.formEquipamento.value)
        .subscribe(
          response => {
            console.log(response);            
            this.showSuccess("Cadastro realizado com Sucesso!");
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
}
