import { Injectable } from '@angular/core';
import { environment } from './../../../environments/environment';
import { Doador } from './Doador';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { FormDoadorComponent } from './form-doador.component';




@Injectable({
    providedIn: 'root'
})

export class DoadorService {

    apiUrl = environment.URLSERVIDOR + "doadores";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
     }

    salvar(novoDoador: any): Observable<FormDoadorComponent>{
        console.log(novoDoador);
        return this.httpClient.post<FormDoadorComponent>(this.apiUrl, JSON.stringify(novoDoador), this.httpOptions)
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

