import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';
import { consultaCepService } from '../services/consultaCEP.service';
import { CadastroDoUsuarioService } from '../cadastro-do-usuario/cadastro-do-usuario.service';

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {
  usuarioLogado: string;
  usuarioLogadoEmail: string;
  idUsuario:Number;
  formDoador: FormGroup;

  constructor(private router: Router,
    private cepService: consultaCepService,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private usuarioService: CadastroDoUsuarioService,
    private zone: NgZone) { }

  ngOnInit(): void {
    this.usuarioLogado = sessionStorage.getItem('isLogado');
    this.usuarioLogadoEmail = sessionStorage.getItem('email');
    //apagar a linha abaixo depois. Só para testar estado da variavel antes de mais nada.
    console.log(`Pagina cadastro de aluno. Variável ususarioLogado: ${this.usuarioLogado}`);

    if(this.usuarioLogado == "" || this.usuarioLogado == null){
      this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '3' }, queryParamsHandling: 'merge' });
    }

  }

}
