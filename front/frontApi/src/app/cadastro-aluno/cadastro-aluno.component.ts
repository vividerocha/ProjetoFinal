import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';
import { consultaCepService } from '../services/consultaCEP.service';
import { CadastroDoUsuarioService } from '../cadastro-do-usuario/cadastro-do-usuario.service';

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

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private usuarioService: CadastroDoUsuarioService,
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
      nomeCompleto: new FormControl(''),
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
    })
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
    if(this.formAluno.get('declaracao').value){
      alert('Marcado');
    }else{
      alert('Não Marcado');
    }
    
    
  }
}
