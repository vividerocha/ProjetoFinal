import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class CadastroDoUsuarioService {

    apiUrl = "http://localhost:8080/usuarios";

    constructor(private httpClient: HttpClient) { }

    salvar(novoUser: any) {
        return this.httpClient.post(this.apiUrl, novoUser);
    }
}