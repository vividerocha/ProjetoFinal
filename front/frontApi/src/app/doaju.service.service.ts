import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class DoajuService {
    apiUrl = "http://localhost:8080/salvarPessoa";

    constructor(private httpClient: HttpClient) { }

    adicionar(novoUser: any) {
        return this.httpClient.post(this.apiUrl, novoUser);
    }
}