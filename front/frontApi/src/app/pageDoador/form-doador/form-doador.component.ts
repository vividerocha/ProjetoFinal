import { isNull } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from './../../cadastro-do-usuario/usuario';

@Component({
  selector: 'app-form-doador',
  templateUrl: './form-doador.component.html',
  styleUrls: ['./form-doador.component.css']
})
export class FormDoadorComponent implements OnInit {

  usuarioLogado: string;

  constructor(private router: Router) { }

  ngOnInit(): void {

    this.usuarioLogado = localStorage.getItem('isLogado');
    console.log(this.usuarioLogado);

    if(this.usuarioLogado == "" || this.usuarioLogado == null){
      //this.router.navigate(['/cadastro-usuario?id=1']);
      this.router.navigate(['/cadastro-usuario'], { queryParams: { id: '1' }, queryParamsHandling: 'merge' });
    }
  }
}
