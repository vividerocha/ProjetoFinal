import { Usuario } from './../cadastro-do-usuario/usuario';
import { Aluno } from './../cadastro-aluno/aluno';
import { Component, OnInit } from '@angular/core';
import { consultaCepService } from './../services/consultaCEP.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, NgForm, Validators } from '@angular/forms';
import { AlunoService } from '../cadastro-aluno/cadastro-aluno.service';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.css']
})
export class AlunosComponent implements OnInit {

  formDetalhe: FormGroup;
  form: FormGroup;
  formularioInvalido: boolean;
  dataSource;
  elements;
  aluno: Aluno;
  usuario: Usuario;
  confirmaExclusao: boolean;
  displayedColumns: string[] = ['id', 'nomeCompleto', 'celular', 'estado', 'cidade', '-'];
  

  constructor(
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private cepService: consultaCepService,
    private toastService: ToastrService
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      pesquisa: new FormControl('')
    });

    this.createForm();
    this.carregaAlunos();
    this.formularioInvalido = false;
    this.detalhaItem(0);
    this.confirmaExclusao = true;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); 
    filterValue = filterValue.toLowerCase(); 
    this.dataSource.filter = filterValue;
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

  carregaAlunos(){
    this.alunoService.getAlunos().subscribe(res=>{
      this.elements = res;
      this.dataSource = new MatTableDataSource(res);
    }, err=>{
      console.log(err);
    });    
  }

  detalhaItem(id: number){
    if(id != 0){
      this.formDetalhe.get('id').setValue(id);
      this.alunoService.getAluno(id).subscribe(res => {
        this.aluno = res;        
        this.usuario = this.aluno.usuario;
      }
      );
    }
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
      let id = this.aluno.id;
      const dados = {
  	      nomeCompleto : this.formDetalhe.get('nomeCompleto').value,
	        cep : this.formDetalhe.get('cep').value,
	        logradouro : this.formDetalhe.get('logradouro').value,
	        numeroCasa : parseInt(this.formDetalhe.get('numeroCasa').value),
	        bairro : this.formDetalhe.get('bairro').value,
	        cidade : this.formDetalhe.get('cidade').value,
	        estado : this.formDetalhe.get('estado').value,
	        complemento : this.formDetalhe.get('complemento').value,
	        telefone : this.formDetalhe.get('telefone').value,
	        celular : this.formDetalhe.get('celular').value,
	        usuario : this.usuario.id
      } as Aluno;
      this.alunoService.atualizar(id, dados).subscribe(() => {      
        this.showSuccess("Cadastro alterado com Sucesso!");
        this.carregaAlunos();
      });
    }else{
      this.formularioInvalido = true;
    }
  }

  excluir(id: number): void {
    console.log("chamando");
    this.alunoService.delete(id)
      .subscribe(
        response => {
          this.showSuccess("Cadastro excluído com Sucesso!");
          this.carregaAlunos();
        },
        error => {
          console.log(error);
        });
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

  confirmaExcluir(){
    this.confirmaExclusao = false;
  }

  desisteExcluir(){
    this.confirmaExclusao = true;
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

}
