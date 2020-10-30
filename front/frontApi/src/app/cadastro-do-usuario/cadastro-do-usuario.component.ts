import { Component, NgZone, OnInit } from '@angular/core';
import { CadastroDoUsuarioService } from './cadastro-do-usuario.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';

import { Usuario } from './usuario';
import { ActivatedRoute, Router } from '@angular/router';




@Component({
  selector: 'app-cadastro-do-usuario',
  templateUrl: './cadastro-do-usuario.component.html',
  styleUrls: ['./cadastro-do-usuario.component.css']
})
export class CadastroDoUsuarioComponent implements OnInit {

  usuario = {} as Usuario;
  usuarios: Usuario[];
  tipoPessoa: string;
  senha: any;
  escondeMsgErroSenha: boolean;
  escondeMsgErroEmail: boolean;
  desabilitaBotao: boolean;
  idUsuarioEncontrado: number = 0;
  erroUser: boolean = true;

  formUsuario: FormGroup;

  constructor(private usuarioService: CadastroDoUsuarioService,
    private fb: FormBuilder, private router: Router,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone) { }

  ngOnInit() {
    if(sessionStorage.getItem('token')!= null){
      this.router.navigate(['/home'])
    }
    if(sessionStorage.getItem('idUser')!==null){
      this.buscaUser(sessionStorage.getItem('idUser'))
    }
    let headers = new Headers();
    headers.append('Accept', 'q=0.8;application/json;q=0.9');
    this.createForm();    
    this.desabilitaBotao = true;
    this.escondeMsgErroSenha = true;
    this.escondeMsgErroEmail = true;
  }

  createForm() {
    this.formUsuario = this.fb.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      senha: new FormControl(''),
      confirmaSenha: new FormControl(''),
      radioTipo: new FormControl('')
    });
  }

  onSubmit(form: NgForm) {

    if (this.formUsuario.valid) {
      console.log('form submitted');

      const dados = {
        usuario: this.formUsuario.value.nome,
        senha: this.formUsuario.value.senha,
        email: this.formUsuario.value.email,
        tipoPermissao: this.formUsuario.value.radioTipo
      } as Usuario;

      console.log(dados);

      this.usuarioService.salvar(dados)
        .subscribe(
          response => {
            console.log(response);
            sessionStorage.setItem('isLogado', 'true');            
            sessionStorage.setItem('idUser', response.id);
            this.redireciona(dados.tipoPermissao)
            //this.goRota();
            //this.submitted = true;
          },
          error => {
            console.log(error);
            alert(error.error);
          });

    } else {
      console.log('form invalido');
      this.desabilitaBotao = true;
    }

  }
  redireciona(tipoUsuario) {

    switch (tipoUsuario) {
      case 'Alunos':
        this.router.navigate(['/cadastro-aluno']);
        break;
      case 'Tecnicos':
        this.router.navigate(['/cadastro-tecnico']);
        break;
      case 'Doadores':
        this.router.navigate(['/page-doador']);
        break;
      default:
        break;
    }

  }
  
  compara(digitado) {
    if (this.formUsuario.value.confirmaSenha != "") {
      if (this.senha == digitado) {
        this.escondeMsgErroSenha = true;
        if (this.escondeMsgErroEmail == true) {
          this.desabilitaBotao = false;
        }
      } else {
        this.escondeMsgErroSenha = false;
        if (this.escondeMsgErroEmail == false) {
          this.desabilitaBotao = true;
        }
      }
    }
  }

  capturaSenha(senha) {
    this.senha = senha;
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    //this.getCars();
    form.resetForm();
    this.usuario = {} as Usuario;
  }

  get nome() {
    return this.formUsuario.get('nome');
  }

  get email() {
    return this.formUsuario.get('email');
  }

  verificaUser(user) {
    if (user == '') {
      return
    }
    this.usuarioService.verificaUser(user)
      .subscribe(dados => {
        console.log('usuario disponível!');
        this.erroUser = true;
      },
        error => {
          console.log('usuário já utilizado!');
          this.erroUser = false;
          this.desabilitaBotao = true;
        });
  }

  verificaEmailExiste(digitado) {
    this.usuarioService.verificaEmail(digitado)
      .subscribe(dados => {
        console.log(dados.id);
        this.idUsuarioEncontrado = dados.id;
        this.escondeMsgErroEmail = false;
        if (this.escondeMsgErroSenha == false) {
          this.desabilitaBotao = false;
        }
      },
        error => {
          console.log(error);
          this.escondeMsgErroEmail = true;
          if (this.escondeMsgErroSenha == true) {
            this.desabilitaBotao = true;
          }
        });

  }

  buscaUser(idUser){
    let id:number = parseInt(idUser);
    this.usuarioService.buscaPorId(id)
    .subscribe(dados => {       
        this.redireciona(dados.grupos[0].nome)
      }, error => {
        console.log(error);
      });
  }

}
