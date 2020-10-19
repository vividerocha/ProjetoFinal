import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Tecnico } from './tecnico';
import { ToastService } from './../toast/toast.service';
import { TecnicoService } from './cadastro-tecnico.service';

@Component({
  selector: 'app-cadastro-tecnico',
  templateUrl: './cadastro-tecnico.component.html',
  styleUrls: ['./cadastro-tecnico.component.css']
})
export class CadastroTecnicoComponent implements OnInit {
  usuarioLogado: string;
  usuarioLogadoEmail: string;
  idUsuario:Number;
  formTecnico: FormGroup;
  formularioInvalido: boolean;
  tiposEquipamentos;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private tecnicoService: TecnicoService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastService) { }

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
    this.formTecnico = this.fb.group({
      nomeCompleto: new FormControl(null,[Validators.required, Validators.minLength(10)]),
      cep: new FormControl(null, [Validators.required, Validators.minLength(8)]),
      estado: new FormControl(''),
      logradouro: new FormControl(''),
      numeroCasa: new FormControl(''),
      bairro: new FormControl(''),
      cidade: new FormControl(''),
      complemento: new FormControl(''),
      telefone: new FormControl(''),
      celular: new FormControl(null, [Validators.required, Validators.minLength(10)])
    });
  }

  consultaCep(){
    let cep = this.formTecnico.get('cep').value;
    console.log(cep);
    
    if(cep != null && cep !== ''){
        this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  populaForm(dados){
    this.formTecnico.patchValue({
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm){
    if(this.formTecnico.valid){
      this.tecnicoService.salvar(this.formTecnico.value).subscribe(() => {        
        console.log(this.formTecnico.value);
        //sessionStorage.setItem('isLogado', '');
        //sessionStorage.setItem('email', '');
        //this.router.navigateByUrl('/login');
        this.showSuccess("Cadastro realizado com Sucesso!");
      });
    }else{
      this.formularioInvalido = true;
    }
  }

  get nomeCompleto(){
    return this.formTecnico.get('nomeCompleto');
  }
  get cep(){
    return this.formTecnico.get('cep');
  }
  get celular(){
    return this.formTecnico.get('celular');
  }
  get numeroCasa(){
    return this.formTecnico.get('numeroCasa');
  }

  showSuccess(mensagem: string) {
    this.toastService.show(mensagem, {
      classname: 'bg-successToast',
      delay: 2000 ,
      autohide: true,
      headertext: 'Toast Header'
    });
  }
}
