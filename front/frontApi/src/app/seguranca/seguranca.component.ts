import { Component, NgZone, OnInit , ViewChild} from '@angular/core';
import { SegurancaService } from './seguranca.service';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { Usuario } from './../cadastro-do-usuario/usuario';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';


@Component({
  selector: 'app-seguranca',
  templateUrl: './seguranca.component.html',
  styleUrls: ['./seguranca.component.css']
})
export class SegurancaComponent implements OnInit {

  displayedColumns: string[] = ['id', 'usuario', 'email', '-'];
  dataSource;
  elements;
  usuario = {} as Usuario;
  usuarios: Usuario[];
  tipoPessoa: string;
  senha: any;
  escondeMsgErroSenha: boolean;
  escondeMsgErroEmail: boolean;
  desabilitaBotao: boolean;
  idUsuarioEncontrado: number = 0;
  erroUser: boolean = true;
  idAdmin: number;
  confirmaExclusao: boolean;
  formularioInvalido: boolean;
  formUsuario: FormGroup;
  formDetalhe: FormGroup;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private service: SegurancaService,
    private fb: FormBuilder, private router: Router,
    private activatedRoute: ActivatedRoute,
    private zone: NgZone,
    private toastService: ToastrService) { }

  ngOnInit() {
    let headers = new Headers();
    headers.append('Accept', 'q=0.8;application/json;q=0.9');
    
    this.createForm();
    this.desabilitaBotao = true;
    this.escondeMsgErroSenha = true;
    this.escondeMsgErroEmail = true;
    this.confirmaExclusao = true;
    this.retornaIdGrupoAdmin();
    this.carregaUsuarios();
  }

  createForm() {
    this.formUsuario = this.fb.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      senha: new FormControl(''),
      confirmaSenha: new FormControl(''),
      radioTipo: new FormControl('')
    });

    this.formDetalhe = this.fb.group({
      id: new FormControl(''),
      nome: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email])
    });
  }

  carregaUsuarios(){
    this.service.getUsuarios()
    .subscribe(res => {
      this.elements = res;
      this.dataSource = new MatTableDataSource(res);
      //this.isLoadingResults = false;
    }, err => {
      console.log(err);
      //this.isLoadingResults = false;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onSubmit(form: NgForm) {

    if (this.formUsuario.valid) {
      console.log('form submitted');

      const dados = {
        usuario: this.formUsuario.value.nome,
        senha: this.formUsuario.value.senha,
        email: this.formUsuario.value.email,
        tipoPermissao: this.idAdmin
      } as Usuario;

      this.service.salvar(dados)
        .subscribe(
          response => {
            console.log(response);
            sessionStorage.setItem('isLogado', 'true');            
            sessionStorage.setItem('idUser', response.id);
            //this.goRota();
            //this.submitted = true;
            this.showSuccess("Cadastro incluído com Sucesso!");
            this.carregaUsuarios();
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

  atualiza(form: NgForm){
    if(this.formDetalhe.valid){
      let id = this.formDetalhe.get('id').value;   
      const dados = {
        usuario: this.formDetalhe.value.nome,
        email: this.formDetalhe.value.email,
        tipoPermissao: this.idAdmin
      } as Usuario;

      this.service.atualizar(id, dados).subscribe(() => {      
        
        //sessionStorage.setItem('isLogado', '');
        //sessionStorage.setItem('email', '');
        //this.router.navigateByUrl('/login');
        this.showSuccess("Cadastro alterado com Sucesso!");
        this.carregaUsuarios();
      });
    }else{
      this.formularioInvalido = true;
    }
  }

  retornaIdGrupoAdmin(){
    this.service.buscaIdAdmin("Admin")
      .subscribe(dados => {
        this.idAdmin = dados.id;
      },
        error => {
          console.log('Não tem grupo Admin Cadastrado no banco!');
        });
    
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
    this.service.verificaUser(user)
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
    this.service.verificaEmail(digitado)
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

  detalhaItem(id: number){
    console.log(id);
    if(id != 0){
      this.formDetalhe.get('id').setValue(id);
      this.service.getUsuario(id).subscribe(res => {
        this.usuario = res;
      }
      );
    }
  }

  confirmaExcluir(){
    this.confirmaExclusao = false;
  }

  desisteExcluir(){
    this.confirmaExclusao = true;
  }

  excluir(id: number): void {
    this.service.delete(id)
      .subscribe(
        response => {
          this.showSuccess("Cadastro excluído com Sucesso!");
          this.carregaUsuarios();
        },
        error => {
          console.log(error);
        });
  }

  showSuccess(mensagem: string) {
    this.toastService.success(mensagem);
  }

}
