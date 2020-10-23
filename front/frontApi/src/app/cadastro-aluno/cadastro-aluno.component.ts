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
  idUsuario:Number;
  formAluno: FormGroup;
  formularioInvalido: boolean;
  tiposEquipamentos;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastrService) { }

  ngOnInit(): void {
    this.usuarioLogado = sessionStorage.getItem('isLogado');
    this.usuarioLogadoEmail = sessionStorage.getItem('email');
    //apagar a linha abaixo depois. Só para testar estado da variavel antes de mais nada.
    console.log(`Pagina cadastro de aluno. Variável ususarioLogado: ${this.usuarioLogado}`);

    //if(this.usuarioLogado == "" || this.usuarioLogado == null){
    //  this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '3' }, queryParamsHandling: 'merge' });
    //}

    this.criaForm();
    this.getTiposEquipamentos();

  }
  criaForm(){
    this.formAluno = this.fb.group({
      nomeCompleto: new FormControl(null,[Validators.required, Validators.minLength(10)]),
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
      tiposEquipamentos : new FormControl(null, [Validators.required])
    });
  }

  consultaCep(){
    let cep = this.formAluno.get('cep').value;
    console.log(cep);
    
    if(cep != null && cep !== ''){
        this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  populaForm(dados){
    this.formAluno.patchValue({
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm){
    if(this.formAluno.valid){
      this.alunoService.salvar(this.formAluno.value).subscribe(() => {        
        console.log(this.formAluno.value);
        //sessionStorage.setItem('isLogado', '');
        //sessionStorage.setItem('email', '');
        //this.router.navigateByUrl('/login');
        this.showSuccess("Cadastro realizado com Sucesso!");
      });
    }else{
      this.formularioInvalido = true;
      alert('Por favor, leia e aceite a declação!');
    }
  }

  getTiposEquipamentos() {
    this.alunoService.getTiposEquipamentos().subscribe(data => {
      this.tiposEquipamentos = data;
    });
  }

  get nomeCompleto(){
    return this.formAluno.get('nomeCompleto');
  }
  get cep(){
    return this.formAluno.get('cep');
  }
  get turma(){
    return this.formAluno.get('turma');
  }
  get serie(){
    return this.formAluno.get('serie');
  }
  get escola(){
    return this.formAluno.get('escola');
  }
  get celular(){
    return this.formAluno.get('celular');
  }
  get numeroCasa(){
    return this.formAluno.get('numeroCasa');
  }

  showSuccess(mensagem: string) {
      this.toastService.success(mensagem);
  }
}
