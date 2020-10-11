import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';
import { Doador } from './Doador';
import { DoadorService } from './form-doador.service';
import { CadastroDoUsuarioService } from './../../cadastro-do-usuario/cadastro-do-usuario.service'
import { Usuario } from 'src/app/cadastro-do-usuario/usuario';

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

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private doadorService: DoadorService,
    private usuarioService: CadastroDoUsuarioService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone) { }

  ngOnInit(): void {

    //localStorage.setItem('isLogado', '');
    this.usuarioLogado = localStorage.getItem('isLogado');
    this.usuarioLogadoEmail = localStorage.getItem('email');
    console.log("email: " + this.usuarioLogadoEmail);
    
    //this.usuarioLogado = "false"
    console.log(this.usuarioLogado);

    if(this.usuarioLogado == "" || this.usuarioLogado == null){
      //this.router.navigate(['/cadastro-usuario?id=1']);
      this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '1' }, queryParamsHandling: 'merge' });
    }

    this.createForm();

    if(this.usuarioLogado != ""){
      this.usuarioService.verificaEmail(this.usuarioLogadoEmail).subscribe(dados =>{
        this.idUsuario = dados[0].id;
        this.formDoador.get('id_usuario').setValue(this.idUsuario);
        }
      );
    }
    
    
  }

  createForm() {
    this.formDoador = this.fb.group({
      nome: new FormControl(''),
      cep: new FormControl(''),
      estado: new FormControl(''),
      logradouro: new FormControl(''),
      numeroCasa: new FormControl(''),
      bairro: new FormControl(''),
      cidade: new FormControl(''),
      complemento: new FormControl(''),
      telefone: new FormControl(''),
      celular: new FormControl(''),
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
      cep: dados.cep,
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm){

      

      this.doadorService.salvar(this.formDoador.value).subscribe(() => {
        //após cadastrar na tabela de pessoas, o usuário será redirecionado para a tela de login
        console.log(this.formDoador.value);
        localStorage.setItem('isLogado', '');
        localStorage.setItem('email', '');
        this.router.navigateByUrl('/login');
      });
      
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
}
