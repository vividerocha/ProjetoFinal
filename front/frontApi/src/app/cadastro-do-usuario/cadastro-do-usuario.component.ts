import { Component, NgZone, OnInit } from '@angular/core';
import { CadastroDoUsuarioService } from './cadastro-do-usuario.service';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';

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
  mostraMsgErroSenha: boolean = false;
  botaoLiberado = true;

  formUsuario: FormGroup;
  
  constructor(private usuarioService: CadastroDoUsuarioService, 
      private fb:FormBuilder, private router: Router, 
      private activatedRoute: ActivatedRoute,
      private zone: NgZone) { }

  ngOnInit() {
    this.createForm();
    
  }
 
  createForm() {
    this.formUsuario = this.fb.group({
      nome: new FormControl(''),
      email: new FormControl(''),
      senha: new FormControl(''),
      confirmaSenha: new FormControl('')
    });
  }

  onSubmit(form: NgForm){
    
      /*if (this.usuario.nome !== undefined) {
        this.usuarioService.updateCar(this.car).subscribe(() => {
          this.cleanForm(form);
        });
      } else {
        */
        this.usuarioService.salvar(this.formUsuario.value).subscribe(() => {
          //this.cleanForm(form);
          //após salvar redireciona para a página de cadastro referente ao id
          localStorage.setItem('isLogado', 'true');
          this.goRota();
          
          //this.router.navigateByUrl('/page-doador');
        });
        
      //}
    
  }

  goRota(){
    this.activatedRoute.queryParams
      .subscribe(params => {
        console.log(params); // { order: "popular" }
        this.tipoPessoa = params.id;
        if(this.tipoPessoa == "1"){
          console.log("entrei aqui");
          this.zone.run(() => {
            this.router.navigate(['/page-doador']);
          });
        }
      }
    );

    
  }

  compara(digitado){
    if (this.formUsuario.value.confirmaSenha != ""){
      if(this.senha == digitado){
        this.mostraMsgErroSenha = true;
        this.botaoLiberado = false;
      }else{
        this.mostraMsgErroSenha = false;
      }
    }
  }

  capturaSenha(senha){
    this.senha = senha;
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    //this.getCars();
    form.resetForm();
    this.usuario = {} as Usuario;
  }
}
