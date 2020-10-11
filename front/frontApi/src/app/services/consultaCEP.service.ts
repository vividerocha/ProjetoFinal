import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class consultaCepService {

    constructor(private httpClient: HttpClient) { }


    consultaEndereco(cep: string){
        //faz replace retirando os caracteres especiais
        cep = cep.replace(/\D/g, '');

        if(cep !== ''){
            //valida cep
            const validacep = /^[0-9]{8}$/;

            //retorna o observable
            return this.httpClient.get('//viacep.com.br/ws/' + cep + '/json');
        }
    }
    
}