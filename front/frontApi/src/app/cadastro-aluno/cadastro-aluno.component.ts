import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { consultaCepService } from '../services/consultaCEP.service';
import { CadastroDoUsuarioService } from '../cadastro-do-usuario/cadastro-do-usuario.service';
import { AlunoService } from './cadastro-aluno.service'

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

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private usuarioService: CadastroDoUsuarioService,
    private alunoService: AlunoService,
    private zone: NgZone) { }

  ngOnInit(): void {
    this.usuarioLogado = sessionStorage.getItem('isLogado');
    this.usuarioLogadoEmail = sessionStorage.getItem('email');
    //apagar a linha abaixo depois. Só para testar estado da variavel antes de mais nada.
    console.log(`Pagina cadastro de aluno. Variável ususarioLogado: ${this.usuarioLogado}`);

    //if(this.usuarioLogado == "" || this.usuarioLogado == null){
    //  this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '3' }, queryParamsHandling: 'merge' });
    //}

    this.criaForm();    

  }
  criaForm(){
    this.formAluno = this.fb.group({
      nomeCompleto: new FormControl(null,[Validators.required, Validators.minLength(10)]),
      cep: new FormControl(''),
      estado: new FormControl(''),
      logradouro: new FormControl(''),
      numeroCasa: new FormControl(''),
      bairro: new FormControl(''),
      cidade: new FormControl(''),
      complemento: new FormControl(''),
      telefone: new FormControl(''),
      celular: new FormControl(''),
      id_usuario: new FormControl(''),
      escola: new FormControl(''),
      serie: new FormControl(''),
      turno: new FormControl(''),
      turma: new FormControl(''),
      notebook: new FormControl(''),
      desktop: new FormControl(''),
      celularEquip: new FormControl(''),
      tablet: new FormControl(''),
      declaracao: new FormControl('')
    });
    console.log(this.formAluno);
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
      cep: dados.cep,
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
      });
    }else{
      this.formularioInvalido = true;
      alert('Por favor, leia e aceite a declação!');
    }
  }
}
