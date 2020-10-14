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
  mostraMsgErroSenha: boolean = true;
  botaoLiberado = true;

  formUsuario: FormGroup;

  constructor(private usuarioService: CadastroDoUsuarioService,
    private fb: FormBuilder, private router: Router,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone) { }

  ngOnInit() {
    let headers = new Headers();
    headers.append('Accept', 'q=0.8;application/json;q=0.9');
    this.createForm();

  }

  createForm() {
    this.formUsuario = this.fb.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      senha: new FormControl(''),
      confirmaSenha: new FormControl('')
    });
  }

  onSubmit(form: NgForm) {

    if (this.formUsuario.valid) {
      console.log('form submitted');

      const dados = {
        nome: this.formUsuario.value.nome,
        senha: this.formUsuario.value.senha,
        email: this.formUsuario.value.email
      } as Usuario;

      this.usuarioService.salvar(dados)
        .subscribe(
          response => {
            console.log(response);
            localStorage.setItem('isLogado', 'true');
            localStorage.setItem('email', dados.email);
            //cad aluno estou usando session ao inves de local
            sessionStorage.setItem('isLogado', 'true');
            sessionStorage.setItem('email', dados.email);
            this.goRota();
            //this.submitted = true;
          },
          error => {
            console.log(error);
            alert(error.error);
          });

    } else {
      console.log('form invalido');
    }

  }

  goRota() {
    this.activatedRoute.queryParams
      .subscribe(params => {
        this.tipoPessoa = params.id;
        if (this.tipoPessoa == "1") {
          this.zone.run(() => {
            this.router.navigate(['/page-doador']);
          });
        } else if (this.tipoPessoa == "2") {
          this.zone.run(() => {
            this.router.navigate(['/cadastro-tecnico']);
          });
        } else if (this.tipoPessoa == "3") {
          this.zone.run(() => {
            this.router.navigate(['/cadastro-aluno']);
          });
        } else {
          this.zone.run(() => {
            this.router.navigate(['/home']);
          });
        }
      }
      );


  }

  compara(digitado) {
    if (this.formUsuario.value.confirmaSenha != "") {
      if (this.senha == digitado) {
        this.mostraMsgErroSenha = true;
        this.botaoLiberado = false;
      } else {
        this.mostraMsgErroSenha = false;
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

}
