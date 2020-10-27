import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { consultaCepService } from './../services/consultaCEP.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Aluno } from './aluno';
import { AlunoService } from './cadastro-aluno.service';
import { ToastrService } from 'ngx-toastr';

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
  formularioInvalido: boolean;
  tiposEquipamentos;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastrService) { }

  ngOnInit(): void {
    this.usuarioLogado = sessionStorage.getItem('isLogado');
    this.usuarioLogadoEmail = sessionStorage.getItem('email');
    //apagar a linha abaixo depois. Só para testar estado da variavel antes de mais nada.
    console.log(`Pagina cadastro de aluno. Variável ususarioLogado: ${this.usuarioLogado}`);

    //if(this.usuarioLogado == "" || this.usuarioLogado == null){
    //  this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '3' }, queryParamsHandling: 'merge' });
    //}

    this.criaForm();
    this.getTiposEquipamentos();

  }
  criaForm(){
    this.formAluno = this.fb.group({
      nomeCompleto: new FormControl(null,[Validators.required, Validators.minLength(10)]),
      cep: new FormControl(null, [Validators.required, Validators.minLength(8)]),
      estado: new FormControl(''),
      logradouro: new FormControl(''),
      numeroCasa: new FormControl(''),
      bairro: new FormControl(''),
      cidade: new FormControl(''),
      complemento: new FormControl(''),
      telefone: new FormControl(''),
      celular: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      escola: new FormControl(null, [Validators.required, Validators.minLength(5)]),
      serie: new FormControl(null, [Validators.required, Validators.minLength(1)]),
      turno: new FormControl(null, [Validators.required]),
      turma: new FormControl(null, [Validators.required, Validators.minLength(1)]),
      declaracao: new FormControl(null, [Validators.required]),
      //tiposEquipamentos : new FormControl(null, [Validators.required]),
      idUser: new FormControl(sessionStorage.getItem('idUser')),
      checkNote: new FormControl(''),
      checkPC: new FormControl(''),
      checkTablet: new FormControl(''),
      checkCelular: new FormControl('')
    });
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
      logradouro: dados.logradouro,
      estado: dados.uf,
      bairro: dados.bairro,
      cidade: dados.localidade
    })
  }

  onSubmit(form: NgForm){
    if(this.formAluno.valid){
      let equip:String [] = new Array();
      if(this.formAluno.value.checkNote){
        equip.push("Notebook")
      }
      if(this.formAluno.value.checkCelular){
        equip.push("Celular")
      }
      if(this.formAluno.value.checkTablet){
        equip.push("Tablet")
      }
      if(this.formAluno.value.checkPC){
        equip.push("PC Desktop")
      }
      const dados = {
        nomeCompleto: this.formAluno.value.nomeCompleto,
        cep: this.formAluno.value.cep,
        logradouro: this.formAluno.value.logradouro,
        numeroCasa: this.formAluno.value.numeroCasa,
        bairro: this.formAluno.value.bairro,
        cidade: this.formAluno.value.cidade,
        estado: this.formAluno.value.estado,
        complemento: this.formAluno.value.complemento,
        telefone: this.formAluno.value.telefone,
        celular: this.formAluno.value.celular,
        escola: this.formAluno.value.escola,
        serie: this.formAluno.value.serie,
        turno: this.formAluno.value.turno,
        turma: this.formAluno.value.turma,
        termo: this.formAluno.value.declaracao,
        usuario: this.formAluno.value.idUser,       
        equipamentos:equip
      } as Aluno
      console.log(equip);
      console.log(dados)
      
      
      //console.log(this.formAluno.value)
      
      this.alunoService.salvar(dados).subscribe(() => {        
        console.log(this.formAluno.value);
        //sessionStorage.setItem('isLogado', '');
        //sessionStorage.setItem('email', '');
        //this.router.navigateByUrl('/login');
        this.showSuccess("Cadastro realizado com Sucesso!");
      });
    }else{
      this.formularioInvalido = true;
      alert('Por favor, leia e aceite a declação!');
    }
  }

  getTiposEquipamentos() {
    this.alunoService.getTiposEquipamentos().subscribe(data => {
      this.tiposEquipamentos = data;
    });
  }

  get nomeCompleto(){
    return this.formAluno.get('nomeCompleto');
  }
  get cep(){
    return this.formAluno.get('cep');
  }
  get turma(){
    return this.formAluno.get('turma');
  }
  get serie(){
    return this.formAluno.get('serie');
  }
  get escola(){
    return this.formAluno.get('escola');
  }
  get celular(){
    return this.formAluno.get('celular');
  }
  get numeroCasa(){
    return this.formAluno.get('numeroCasa');
  }

  showSuccess(mensagem: string) {
      this.toastService.success(mensagem);
  }
}
