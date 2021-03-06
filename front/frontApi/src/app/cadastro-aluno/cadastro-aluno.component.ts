import { TipoEquipamento } from './../cadastro-tipo-equipamento/tipoEquipamento';
import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Aluno } from './aluno';
import { AlunoService } from './cadastro-aluno.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {
  usuarioLogado: string;
  usuarioLogadoEmail: string;
  idUsuario: Number;
  formAluno: FormGroup;
  formularioInvalido: boolean;
  equipamentosSelecionados: String[] = new Array();
  tiposEquipamentos;
  aluno: any;
  check:boolean = false;
  salvaOuEdita:boolean = true;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastrService) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('idAluno') != null) {
      this.populaFormulario();
      this.populaEquipamento();      
    } else if (sessionStorage.getItem('idUser') == null) {
      this.router.navigate(['/home'])
    }
    this.criaForm();
    this.getTiposEquipamentos();

    if(this.aluno !=undefined){
      this.carregaDadosForm(this.aluno);
      this.consultaCep();
      this.salvaOuEdita = false;
    }

  }
  criaForm() {
    this.formAluno = this.fb.group({
      nomeCompleto: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      cep: new FormControl(null, [Validators.required, Validators.minLength(8)]),
      estado: new FormControl(''),
      logradouro: new FormControl(''),
      numeroCasa: new FormControl(''),
      bairro: new FormControl(''),
      cidade: new FormControl(''),
      complemento: new FormControl(''),
      telefone: new FormControl(''),
      celular: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      escola: new FormControl(null, [Validators.required, Validators.minLength(5)]),
      serie: new FormControl(null, [Validators.required, Validators.minLength(1)]),
      turno: new FormControl(null, [Validators.required]),
      turma: new FormControl(null, [Validators.required, Validators.minLength(1)]),
      declaracao: new FormControl(null, [Validators.required]),
      //tiposEquipamentos : new FormControl(null, [Validators.required]),
      idUser: new FormControl(sessionStorage.getItem('idUser')),
      checkCelular: new FormControl('')
    });
  }

  consultaCep() {
    let cep = this.formAluno.get('cep').value;
    console.log(cep);

    if (cep != null && cep !== '') {
      this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  itemSelecionado(ev) {
    console.log(ev)
    // se estiver marcado adiciona ao array
    if (ev.target.checked) {
      this.equipamentosSelecionados.push(ev.target.value)
      //se o usuario desmarcar o else tira do array o equipamento
    } else {
      this.equipamentosSelecionados.splice(this.equipamentosSelecionados.indexOf(ev.target.value), 1)
    }
  }

  populaForm(dados) {
    this.formAluno.patchValue({
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  salva(){
    const dados = this.capturaDados();
    this.alunoService.salvar(dados).subscribe(() => {
      console.log(this.formAluno.value);
      this.showSuccess("Cadastro realizado com Sucesso!");
      sessionStorage.clear();
      this.router.navigate(['/login'])
    });
  }

  atualiza(){
    const dados = this.capturaDados();
    const id: number = this.aluno.id;
    console.log(dados);
    this.alunoService.atualizar(id, dados )
    .subscribe(()=>{
      this.toastService.success("Dados atualizados com Sucesso!");
      sessionStorage.setItem('reiniciaMenu', 'ok')
      this.router.navigate(['/home'])
      //location.reload();
    })
  }

  capturaDados(){
    if (this.formAluno.valid) {
      if(this.aluno ==undefined){
        console.log("if")       
        const dados = {
          nomeCompleto: this.formAluno.value.nomeCompleto,
          cep: this.formAluno.value.cep,
          logradouro: this.formAluno.value.logradouro,
          numeroCasa: this.formAluno.value.numeroCasa,
          bairro: this.formAluno.value.bairro,
          cidade: this.formAluno.value.cidade,
          estado: this.formAluno.value.estado,
          complemento: this.formAluno.value.complemento,
          telefone: this.formAluno.value.telefone,
          celular: this.formAluno.value.celular,
          escola: this.formAluno.value.escola,
          serie: this.formAluno.value.serie,
          turno: this.formAluno.value.turno,
          turma: this.formAluno.value.turma,
          termo: this.formAluno.value.declaracao,
          usuario: this.formAluno.value.idUser,
          equipamentos: this.equipamentosSelecionados
        } as Aluno
        return dados; 
      }else{
        console.log("else") 
        const dados = {
          nomeCompleto: this.formAluno.value.nomeCompleto,
          cep: this.formAluno.value.cep,
          logradouro: this.formAluno.value.logradouro,
          numeroCasa: this.formAluno.value.numeroCasa,
          bairro: this.formAluno.value.bairro,
          cidade: this.formAluno.value.cidade,
          estado: this.formAluno.value.estado,
          complemento: this.formAluno.value.complemento,
          telefone: this.formAluno.value.telefone,
          celular: this.formAluno.value.celular,
          escola: this.formAluno.value.escola,
          serie: this.formAluno.value.serie,
          turno: this.formAluno.value.turno,
          turma: this.formAluno.value.turma,
          termo: this.formAluno.value.declaracao,
          usuario: sessionStorage.idUser || sessionStorage.idUserLogado,
          equipamentos: this.equipamentosSelecionados,
          id: this.aluno.id
        } as Aluno
        return dados;
      }
    }else{
      this.formularioInvalido = true;
      alert('Por favor, leia e aceite a declação!');
    }
  }

  onSubmit(form: NgForm) {
    if(this.salvaOuEdita){
      this.salva();
    }else{
      this.atualiza();
    }
  }

  getTiposEquipamentos() {
    this.alunoService.getTiposEquipamentos().subscribe(data => {
      this.tiposEquipamentos = data;
      //console.log(this.tiposEquipamentos);
      
      for (let i = 0; i < this.tiposEquipamentos.length; i++) {
        for(let j = 0; j < this.equipamentosSelecionados.length; j++){
          if(this.tiposEquipamentos[i].descricao == this.equipamentosSelecionados[j]){
            this.tiposEquipamentos[i].equipamentos = true;
          }
        }        
      }
      console.log(this.tiposEquipamentos);
    });
  }

  get nomeCompleto() {
    return this.formAluno.get('nomeCompleto');
  }
  get cep() {
    return this.formAluno.get('cep');
  }
  get turma() {
    return this.formAluno.get('turma');
  }
  get serie() {
    return this.formAluno.get('serie');
  }
  get escola() {
    return this.formAluno.get('escola');
  }
  get celular() {
    return this.formAluno.get('celular');
  }
  get numeroCasa() {
    return this.formAluno.get('numeroCasa');
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }
  populaFormulario() {
    this.activatedRoute.queryParams
      .subscribe(params => {
        this.aluno = {
          id: params.id,
          nomeCompleto: params.nomeCompleto,
          cep: params.cep,
          logradouro: params.logradouro,
          numeroCasa: params.numeroCasa,
          bairro: params.bairro,
          cidade: params.cidade,
          estado: params.estado,
          complemento: params.complemento,
          telefone: params.telefone,
          celular: params.celular,
          escola: params.escola,
          serie: params.serie,
          turno: params.turno,
          turma: params.turma,
          termo: params.termo,
          tiposEquipamentos: params.TipoEquipamento,
          equipamentos: params.equipamentos,
          usuario: params.usuario
        }
      })
  }
populaEquipamento(){
  const equip = JSON.parse(sessionStorage.getItem('equipamentos'));  
  for (let i = 0; i < equip.length; i++) {
    this.equipamentosSelecionados.push(equip[i].descricao);      
  }
  console.log(this.equipamentosSelecionados);
}
carregaDadosForm(dados){
  this.formAluno.patchValue({
    nomeCompleto: dados.nomeCompleto,
    cep: dados.cep,
    numeroCasa: dados.numeroCasa,
    complemento: dados.complemento,
    telefone: dados.telefone,
    celular: dados.celular,
    escola: dados.escola,
    serie: dados.serie,
    turma: dados.turma,
    turno: dados.turno
  })
}

}