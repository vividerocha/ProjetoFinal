import { Component, OnInit } from '@angular/core';
import { ContatoService } from './../contato/contato.service'
import { Mensagem } from './../contato/mensagem';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-mensagem',
  templateUrl: './mensagem.component.html',
  styleUrls: ['./mensagem.component.css']
})
export class MensagemComponent implements OnInit {
  
  detalhaS: boolean;
  dataSource;
  mensagem: Mensagem;
  elements;
  displayedColumns: string[] = ['id','destinatario', 'assunto',  '-'];
  

  constructor(private service: ContatoService) { }

  ngOnInit(): void {
    this.detalhaS = false;
    this.carregaMensagens();
  }

  detalhaItem(id: number){
    if(id != 0){
      this.service.getMensagem(id).subscribe(res => {
        this.mensagem = res;
        this.detalhaS = true;
      }
      );
    }
  }

  carregaMensagens() {
    this.service.getMensagens()
    .subscribe(res => {
      this.elements = res;
      this.dataSource = new MatTableDataSource(res);
      //this.isLoadingResults = false;
    }, err => {
      console.log(err);
      //this.isLoadingResults = false;
    });
  }

  fechaSituacao(){
    this.detalhaS = false;
  }
}
