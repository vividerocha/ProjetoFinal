import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Doador } from './doador';
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


  formDoador: FormGroup;
  //usuario: any[] = [];
  usuario: Usuario;
  doador: Doador;
  salvaOuEdita:boolean = true;
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
    //IdDoador é setado no compomente menu, o link traz a informações na URL, se setado,  popula o form.
    // o else não permite que alguém que não esteja vindo pagina de cadastro ou esteja logado acesse esse form.
    if (sessionStorage.getItem('idDoador') != null) {      
      this.populaFormulario();
    } else if (sessionStorage.getItem('idUser') == null) {
      this.router.navigate(['/home'])
    }
    this.formularioInvalido = false;
    this.createForm();

    // verifica se o doador etá vazio, se não ele preenche o formulario
    if(this.doador != undefined){
      this.preencheFormEditar(this.doador);
      this.consultaCep();
      this.salvaOuEdita = false
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

    });
  }

  consultaCep() {
    let cep = this.formDoador.get('cep').value;
    console.log(cep);

    if (cep != null && cep !== '') {
      this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  populaForm(dados) {
    this.formDoador.patchValue({
      //cep: dados.cep,
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  salva(){
    const dados =  this.capturaDados();
    this.doadorService.salvar(dados).subscribe(() => {
      this.toastService.success("Cadastro realizado com Sucesso!");
      sessionStorage.clear();
      this.router.navigate(['/login'])
    });
  }

  atualiza(){
    const dados =  this.capturaDados();    
    const id: number = this.doador.id;
    this.doadorService.atualizar(id, dados )
    .subscribe(()=>{
      this.toastService.success("Dados atualizados com Sucesso!");
      sessionStorage.setItem('reiniciaMenu', 'ok')
      this.router.navigate(['/home'])
      //location.reload();
    })
  }

  capturaDados(){
    if (this.formDoador.valid) {
      if(this.doador == undefined){
        const dados = {
          nomeCompleto: this.formDoador.value.nomeCompleto,
          cep: this.formDoador.value.cep,
          logradouro: this.formDoador.value.logradouro,
          numeroCasa: this.formDoador.value.numeroCasa,
          bairro: this.formDoador.value.bairro,
          cidade: this.formDoador.value.cidade,
          estado: this.formDoador.value.estado,
          complemento: this.formDoador.value.complemento,
          celular: this.formDoador.value.celular,
          telefone: this.formDoador.value.telefone,
          usuario: sessionStorage.idUser || sessionStorage.idUserLogado          
        } as Doador
       return dados;
      }else{
      const dados = {
        nomeCompleto: this.formDoador.value.nomeCompleto,
        cep: this.formDoador.value.cep,
        logradouro: this.formDoador.value.logradouro,
        numeroCasa: this.formDoador.value.numeroCasa,
        bairro: this.formDoador.value.bairro,
        cidade: this.formDoador.value.cidade,
        estado: this.formDoador.value.estado,
        complemento: this.formDoador.value.complemento,
        celular: this.formDoador.value.celular,
        telefone: this.formDoador.value.telefone,
        usuario: sessionStorage.idUser || sessionStorage.idUserLogado,
        id: this.doador.id
      } as Doador
     return dados;
    }   
    }else {
      this.formularioInvalido = true;
    }
  }

  onSubmit() {
    if(this.salvaOuEdita){
      this.salva();
    }else{
      this.atualiza();
     }
  }


  populaFormulario() {    
    this.activatedRoute.queryParams
      .subscribe(params => {
        this.doador = {
          nomeCompleto: params.nomeCompleto,
          id: params.id,
          celular: params.celular,
          cep: params.cep,
          complemento: params.complemento,
          telefone: params.telefone,
          usuario: params.usuario,
          numeroCasa: params.numeroCasa,
          cidade: params.cidade,
          bairro: params.bairro,
          logradouro: params.logradouro
        } as Doador
        
      }
      );

  }

  preencheFormEditar(dados) {
    console.log(dados);
    this.formDoador.patchValue({
      nomeCompleto: dados.nomeCompleto,
      cep: dados.cep,
      numeroCasa: dados.numeroCasa,
      complemento: dados.complemento,
      telefone: dados.telefone,
      celular: dados.celular
    })
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

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

}
