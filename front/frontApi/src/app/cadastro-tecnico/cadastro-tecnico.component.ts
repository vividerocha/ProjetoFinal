import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Tecnico } from './tecnico';
import { TecnicoService } from './cadastro-tecnico.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cadastro-tecnico',
  templateUrl: './cadastro-tecnico.component.html',
  styleUrls: ['./cadastro-tecnico.component.css']
})
export class CadastroTecnicoComponent implements OnInit {
  
  formTecnico: FormGroup;
  formularioInvalido: boolean;
  tiposEquipamentos;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private tecnicoService: TecnicoService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastrService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('token')!= null){
      this.router.navigate(['/home'])
    }
    this.criaForm();
  }

  criaForm() {
    this.formTecnico = this.fb.group({
      nomeCompleto: new FormControl(null, [Validators.required, Validators.minLength(10)]),
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

  consultaCep() {
    let cep = this.formTecnico.get('cep').value;
    console.log(cep);

    if (cep != null && cep !== '') {
      this.cepService.consultaEndereco(cep).subscribe(dados => this.populaForm(dados));
    }
  }

  populaForm(dados) {
    this.formTecnico.patchValue({
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm) {
    if (this.formTecnico.valid) {
      const dados = {
        nomeCompleto: this.formTecnico.value.nomeCompleto,
        cep: this.formTecnico.value.cep,
        logradouro: this.formTecnico.value.logradouro,
        numeroCasa: this.formTecnico.value.numeroCasa,
        bairro: this.formTecnico.value.bairro,
        cidade: this.formTecnico.value.cidade,
        estado: this.formTecnico.value.estado,
        complemento: this.formTecnico.value.complemento,
        telefone: this.formTecnico.value.telefone,
        celular: this.formTecnico.value.celular,
        termo: true,
        usuario: sessionStorage.getItem('idUser')
      } as Tecnico

      this.tecnicoService.salvar(dados).subscribe(() => {
        this.showSuccess("Cadastro realizado com Sucesso!");
        this.router.navigate(['/login'])
      });
    } else {
      this.formularioInvalido = true;
    }
  }

  get nomeCompleto() {
    return this.formTecnico.get('nomeCompleto');
  }
  get cep() {
    return this.formTecnico.get('cep');
  }
  get celular() {
    return this.formTecnico.get('celular');
  }
  get numeroCasa() {
    return this.formTecnico.get('numeroCasa');
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }
}
