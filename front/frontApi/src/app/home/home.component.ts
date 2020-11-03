import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    // caso a home seja iniciada a partir da tela de atualização, os sistema atualiza a home.
    if(sessionStorage.getItem('reiniciaMenu')!= null){
      sessionStorage.removeItem('reiniciaMenu');
      location.reload();
    }   
  }

}
