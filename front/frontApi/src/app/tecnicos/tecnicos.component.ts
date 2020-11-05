import { Component, OnInit, ViewChild } from '@angular/core';
import { TecnicosService } from './tecnicos.service';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { FormBuilder, FormGroup, FormControl,  NgForm, Validators } from '@angular/forms';
import { consultaCepService } from './../services/consultaCEP.service';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { Tecnico } from './../cadastro-tecnico/tecnico';

@Component({
  selector: 'app-tecnicos',
  templateUrl: './tecnicos.component.html',
  styleUrls: ['./tecnicos.component.css']
})
export class TecnicosComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nomeCompleto', 'celular', 'estado', 'cidade', '-'];
  dataSource;
  elements;
  form: FormGroup;
  formDetalhe: FormGroup;
  formularioInvalido: boolean;
  tecnico: Tecnico;
  confirmaExclusao: boolean;
  usuarioTec: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  constructor(private service: TecnicosService,
              private fb: FormBuilder,
              private cepService: consultaCepService,
              private toastService: ToastrService) {
    
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      pesquisa: new FormControl('')
    });
    
    this.carregaTodos();
    this.createForm();
    this.formularioInvalido = false;
    this.confirmaExclusao = true;
    this.detalhaItem(0);
  }

  carregaTodos(){
    this.service.getDoadores()
    .subscribe(res => {
      console.log(res)
      this.elements = res;
      this.dataSource = new MatTableDataSource(res);
      //this.isLoadingResults = false;
    }, err => {
      console.log(err);
      //this.isLoadingResults = false;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); 
    filterValue = filterValue.toLowerCase(); 
    this.dataSource.filter = filterValue;
  }

  detalhaItem(id: number){
    if(id != 0){
      this.formDetalhe.get('id').setValue(id);
      this.service.getDoador(id).subscribe(res => {
        this.tecnico = res;
        this.usuarioTec = this.tecnico.usuario;
        console.log(this.usuarioTec.id);
      }
      );
    }
  }

  get nomeCompleto() {
    return this.formDetalhe.get('nomeCompleto');
  }

  get cep() {
    return this.formDetalhe.get('cep');
  }

  get celular() {
    return this.formDetalhe.get('celular');
  }

  get numeroCasa() {
    return this.formDetalhe.get('numeroCasa');
  }

  createForm() {
    this.formDetalhe = this.fb.group({
      id: new FormControl(''),
      nomeCompleto: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      cep: new FormControl(null, [Validators.required, Validators.minLength(8)]),
      estado: new FormControl(''),
      logradouro: new FormControl(''),
      numeroCasa: new FormControl(null, [Validators.required, Validators.minLength(1)]),
      bairro: new FormControl(''),
      cidade: new FormControl(''),
      complemento: new FormControl(''),
      telefone: new FormControl(''),
      celular: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      usuario: new FormControl('')
    });
  }

  consultaCep(){
    let cep = this.formDetalhe.get('cep').value;
    console.log(cep);
    
    if(cep != null && cep !== ''){
        this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  populaForm(dados){
    this.formDetalhe.patchValue({
      //cep: dados.cep,
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm){
    if(this.formDetalhe.valid){
      let id = this.formDetalhe.get('id').value;   
      console.log(this.formDetalhe.value);
      this.service.atualizar(id, this.formDetalhe.value).subscribe(() => {      
        
        //sessionStorage.setItem('isLogado', '');
        //sessionStorage.setItem('email', '');
        //this.router.navigateByUrl('/login');
        this.showSuccess("Cadastro alterado com Sucesso!");
        this.carregaTodos();
      });
    }else{
      this.formularioInvalido = true;
    }
  }

  confirmaExcluir(){
    this.confirmaExclusao = false;
  }

  desisteExcluir(){
    this.confirmaExclusao = true;
  }

  excluir(id: number): void {
    console.log("chamando");
    this.service.delete(id)
      .subscribe(
        response => {
          this.showSuccess("Cadastro excluído com Sucesso!");
          this.carregaTodos();
        },
        error => {
          console.log(error);
        });
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

}

