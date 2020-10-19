import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { CadastroTecnicoComponent } from './cadastro-tecnico.component';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class TecnicoService {

    apiUrl = environment.URLSERVIDOR + "tecnicos";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    salvar(novoTecnico: CadastroTecnicoComponent): Observable<CadastroTecnicoComponent>{
        console.log(novoTecnico);
        return this.httpClient.post<CadastroTecnicoComponent>(this.apiUrl, JSON.stringify(novoTecnico), this.httpOptions)
        .pipe(retry(2),catchError(this.handleError))
        //return this.httpClient.post(this.apiUrl, novoUser);
        
    }

    // Manipulação de erros
    handleError(error: HttpErrorResponse) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
        // Erro ocorreu no lado do client
        errorMessage = error.error.message;
        } else {
        // Erro ocorreu no lado do servidor
        errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
        }
        console.log(errorMessage);
        return throwError(errorMessage);
    };

}