import { Component, OnInit } from '@angular/core';
import { EquipamentosReparo } from './equipamentosReparo';
import { QuadroTecnicoService } from './quadro-tecnico.service';

@Component({
  selector: 'app-quadro-tecnico',
  templateUrl: './quadro-tecnico.component.html',
  styleUrls: ['./quadro-tecnico.component.css']
})
export class QuadroTecnicoComponent implements OnInit {

  elementos: EquipamentosReparo;
  

  constructor(private service: QuadroTecnicoService) { }

  ngOnInit(): void {
    this.buscaEquipamentosParaReparo();
  }

  buscaEquipamentosParaReparo(){
      this.service.getEquipamentos(parseInt(sessionStorage.getItem('idUserLogado')))
      .subscribe(res => {
        this.elementos = res;
      }, err => {
        console.log(err);
      });
    
  }
}
