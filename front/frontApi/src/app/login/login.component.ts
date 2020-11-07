import { Usuario } from './../cadastro-do-usuario/usuario';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from './login';
import { AuthService } from './../seguranca/auth.service'
import { LoginService } from './login.service';
import { CadastroDoUsuarioService } from '../cadastro-do-usuario/cadastro-do-usuario.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  escondeMensagemErro: Boolean;
  ver: boolean = true;
  verErro: boolean = true;
  verErroSenha: boolean = true;
  novaSenhaDigitada:String;
  botaoDisable: boolean = false;
  user: Usuario;


  constructor(private fb: FormBuilder,
    private router: Router,
    private service: AuthService,
    private loginService: LoginService,
    private caduser: CadastroDoUsuarioService,
    private toastService: ToastrService) { }

  ngOnInit(): void {  
    this.createForm();
    this.escondeMensagemErro = true;  
    if (sessionStorage.getItem('token') !== null) {
      this.router.navigate(['/home']);
    }

  }

  createForm() {
    this.formLogin = this.fb.group({
      email: new FormControl(''),
      senha: new FormControl(''),
      nomeCompleto:new FormControl(''),
      emailRec:new FormControl(''),
      novaSenhaRec:new FormControl(''),
      novaSenha2Rec:new FormControl('')
    });
  }
  onSubmit(form: NgForm) {
    if (this.formLogin.invalid) {
      return;
    }
    //fazer a chamada  ao service
    this.service.login(this.formLogin.value.email, this.formLogin.value.senha);

  }

  decide(){
    if(!this.ver){
      this.novaSenha();
    }else {
      this.recuperarSenha();
    }
  }

  novaSenha(){
    const dados = {
      usuario: this.user.usuario,
      id: this.user.id,
      senha: this.formLogin.value.novaSenhaRec,
      email: this.user.email
    }
    const id = this.user.id;
    this.loginService.atualizar(id, dados)
    .subscribe(dados => {
      console.log(dados);
      this.toastService.success("Dados atualizados com Sucesso!");
    },
      error => {
        console.log(error);        
      });
  }

  recuperarSenha(){
    // inserir controle de erros    
    const nome = this.formLogin.value.nomeCompleto
    const email = this.formLogin.value.emailRec
    this.loginService.recupera (email, nome)
    .subscribe(dados => {
      this.user = dados;
      console.log(this.user);
      this.ver = false;
      this.verErro = true;
      this.botaoDisable = true;
    },
      error => {
        console.log(error);
        this.ver = true;
        this.verErro = false;
      });
  }

  capturaSenha(s){
    this.novaSenhaDigitada = s;
  }
  compara(s){
    if(s == this.novaSenhaDigitada){
      this.verErroSenha = true;
      this.botaoDisable = false;
    }else{
      this.verErroSenha = false;
      this.botaoDisable = true;
    }
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

}
