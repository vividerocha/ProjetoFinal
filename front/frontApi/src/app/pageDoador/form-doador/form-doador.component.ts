import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-doador',
  templateUrl: './form-doador.component.html',
  styleUrls: ['./form-doador.component.css']
})
export class FormDoadorComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {

    let usuarioLogado : boolean = false;

    if(usuarioLogado == false){
      this.router.navigate(['/cadastro-usuario']);
    }
  }

}
[{ "id": 1, "nome": "Beronice Alves dos Reis", "telefone": "62 9 92947507", "email": "Beronice@gmail.com" }, { "id": 2, "nome": "Anisberto dos Reis do Nascimento", "telefone": "62 9 92947507", "email": "Anisberto@gmail.com" }, { "id": 3, "nome": "Aline dos Reis do Nascimento", "telefone": "62 9 92947507", "email": "Aline@gmail.com" }]