import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Doador } from './Doador';
import { DoadorService } from './form-doador.service';
import { CadastroDoUsuarioService } from './../../cadastro-do-usuario/cadastro-do-usuario.service'
import { Usuario } from 'src/app/cadastro-do-usuario/usuario';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-form-doador',
  templateUrl: './form-doador.component.html',
  styleUrls: ['./form-doador.component.css']
})
export class FormDoadorComponent implements OnInit {

  usuarioLogado: string;
  usuarioLogadoEmail: string;
  idUsuario:Number;
  formDoador: FormGroup;
  //usuario: any[] = [];
  usuario: Usuario;
  formularioInvalido: boolean;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private doadorService: DoadorService,
    private usuarioService: CadastroDoUsuarioService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastrService) { }

  ngOnInit(): void {

    //localStorage.setItem('isLogado', '');
    this.usuarioLogado = sessionStorage.getItem('isLogado');
    this.usuarioLogadoEmail = sessionStorage.getItem('email');

    //if(this.usuarioLogado == "" || this.usuarioLogado == null){
    //  this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '1' }, queryParamsHandling: 'merge' });
    //}

    this.formularioInvalido = false;
    this.createForm();

    if(this.usuarioLogadoEmail != "" && this.usuarioLogadoEmail != null){
      this.usuarioService.verificaEmail(this.usuarioLogadoEmail).subscribe(dados =>{
        this.idUsuario = dados.id;
        console.log(dados);
        this.formDoador.get('id_usuario').setValue(this.idUsuario);
        }
      );
    }
    
    
  }

  createForm() {
    this.formDoador = this.fb.group({
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
      id_usuario: new FormControl('')
    });
  }

  consultaCep(){
    let cep = this.formDoador.get('cep').value;
    console.log(cep);
    
    if(cep != null && cep !== ''){
        this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  populaForm(dados){
    this.formDoador.patchValue({
      //cep: dados.cep,
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm){

    if(this.formDoador.valid){
      this.doadorService.salvar(this.formDoador.value).subscribe(() => {
        //após cadastrar na tabela de pessoas, o usuário será redirecionado para a tela de login
        console.log(this.formDoador.value);
        //sessionStorage.setItem('isLogado', '');
        //sessionStorage.setItem('email', '');
        //this.router.navigateByUrl('/login');
        this.toastService.success("Cadastro realizado com Sucesso!");
      });
    }else{
      this.formularioInvalido = true;
    }

    //}
  
}


  goRota(){
    this.activatedRoute.queryParams
      .subscribe(params => {
        console.log(params); // { order: "popular" }
        this.zone.run(() => {
            this.router.navigate(['/cadastro-equipamento']);
          });
      }
    );

  }

  get nomeCompleto() {
    return this.formDoador.get('nomeCompleto');
  }

  get cep() {
    return this.formDoador.get('cep');
  }

  get celular() {
    return this.formDoador.get('celular');
  }

  get numeroCasa() {
    return this.formDoador.get('numeroCasa');
  }

  showSucesso(mensagem: string){
    this.toastService.success(mensagem, '', {
      positionClass : "toast-center",
      closeButton : false,
      newestOnTop : false,
      progressBar : false,
      timeOut : 5000,
      extendedTimeOut : 1000,
      easing : 'ease-in'
     }
    );
  }
  
}
